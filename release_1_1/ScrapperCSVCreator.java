package release_1_1;

import java.io.IOException;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

public class ScrapperCSVCreator {
    private File csv = new File("res\\file_sorgenti\\soccerstats.com");

    public ScrapperCSVCreator() {
        creaCsvSorgenti();
    }


    // crea i csv sorgenti da soccerstats.com per ogni campionato
    public void creaCsvSorgenti() {
            ArrayList<String> linkSorgentiSoccerstats = creaListaLinkSorgenti13();

            for (String linkSoccerstats : linkSorgentiSoccerstats) {

                // metodo scrapper
                ArrayList<String> elementiServ = leggiHTMLGet13(linkSoccerstats);

                try {
                    File file = new File(csv + "\\" + trovaSigla(linkSoccerstats) + ".csv");
                    if (!file.exists() || scaricareSorgenti(file)) {
                        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                        CSVWriter csvWriter = new CSVWriter(bw, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
                        // rimuove righe inutili
                        for (int i = 0; i < 1; i++) {
                            elementiServ.remove(0);
                        }
                        for (int i = elementiServ.size() - 1; i > 0; i--) {
                            if (!elementiServ.get(i).equals("+")) {
                                elementiServ.remove(elementiServ.size() - 1);
                            } else {
                                break;
                            }
                        }

                        // seleziona elementi e crea csv
                        String[] header = {"Div", "HomeTeam", "AwayTeam", "FTHG", "FTAG", "FTR", "HTHG", "HTAG", "HTR"};
                        csvWriter.writeNext(header);
                        String rigaserv = "";
                        String[] arrayVuoto = new String[9];

                        for (int i = 0; i < elementiServ.size(); i++) {
                            if (elementiServ.get(i).contains(" - ")) {
                                String div = trovaSigla(linkSoccerstats);
                                String homeTeam = elementiServ.get(i - 1);
                                String awayTeam = elementiServ.get(i + 1);
                                String[] result = elementiServ.get(i).split(" - ");
                                String fthg = result[0];
                                String ftag = result[1];
                                String ftr = generaR(fthg,ftag);
                                rigaserv += div + "," + homeTeam + "," + awayTeam + "," + fthg + "," + ftag + "," + ftr + ",";
                            } else if (elementiServ.get(i).contains("(")) {
                                String[] HTResult = elementiServ.get(i).replace("(", "").replace(")", "").split("-");
                                String hthg = HTResult[0];
                                String htag = HTResult[1];
                                String htr = generaR(hthg,htag);
                                rigaserv += hthg + "," + htag + "," + htr;
                                String[] riga = rigaserv.split(",");
                                csvWriter.writeNext(riga);
                                rigaserv = "";
                                riga = arrayVuoto;
                            }
                        }
                        System.out.println("File " + trovaSigla(linkSoccerstats) + ".csv creato e salvato nella cartella sorgenti");
                        elementiServ.clear();
                        csvWriter.close();
                    }else{
                        System.out.println("Non necessario scaricare file sorgenti");
                        break;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
        }
    }


    // ------------METODI AUSILIARI----------------------

    // crea una lista di partite su cui scommettere
    public ArrayList<Match> creaListaPartiteDaGiocare(ArrayList<Squadra> listaSquadreAggiornata) {
        ArrayList<Match> listaPartiteOggi = new ArrayList<>();
        ArrayList<String> elementiServ = new ArrayList<>();
        ArrayList<String> linkSorgentiSoccerstats = creaListaLinkSorgenti13();

        System.out.println("FILTRANDO LE PARTITE DEL GIORNO...");

        for (String linkSoccerstats : linkSorgentiSoccerstats) {
            elementiServ.addAll(leggiHTMLGet13(linkSoccerstats));
        }

        for (int i = 0; i < elementiServ.size(); i++) {
            Match match = new Match();
            if (elementiServ.get(i).equals("h2h")) {
                if (daGiocare(elementiServ.get(i - 4), elementiServ.get(i - 2))) {
                    match.setDate(elementiServ.get(i - 4));
                    Squadra home = getSquadraByName(elementiServ.get(i - 3), listaSquadreAggiornata);
                    match.sethomeTeam(home);
                    match.setTime(elementiServ.get(i - 2));
                    Squadra away = getSquadraByName(elementiServ.get(i - 1), listaSquadreAggiornata);
                    match.setawayTeam(away);
                    match.setDiv(home.getLeague());
                    listaPartiteOggi.add(match);
                }
            }
        }

        // assegna probabilità ad ogni partita
        for (Match partita : listaPartiteOggi) {
            double XHTProb = (partita.getHomeTeam().getXHTHomeProb() + partita.getAwayTeam().getXHTAwayProb()) / 2;
            double XFTProb = (partita.getHomeTeam().getXFTHomeProb() + partita.getAwayTeam().getXFTAwayProb()) / 2;
            double ov05HTProb = (partita.getHomeTeam().getOv05HTHomeProb() + partita.getAwayTeam().getOv05HTAwayProb()) / 2;
            double ov15HTProb = (partita.getHomeTeam().getOv15HTHomeProb() + partita.getAwayTeam().getOv15HTAwayProb()) / 2;
            double un05HTProb = (partita.getHomeTeam().getUn05HTHomeProb() + partita.getAwayTeam().getUn05HTAwayProb()) / 2;
            double un15HTProb = (partita.getHomeTeam().getUn15HTHomeProb() + partita.getAwayTeam().getUn15HTAwayProb()) / 2;
            double GGHTProb = (partita.getHomeTeam().getGGHTHomeProb() + partita.getAwayTeam().getGGHTAwayProb()) / 2;
            double HTFTXXProb = (partita.getHomeTeam().getHTFTXXHomeProb() + partita.getAwayTeam().getHTFTXXAwayProb()) / 2;
            double ov25FTProb = (partita.getHomeTeam().getOv25FTHomeProb() + partita.getAwayTeam().getOv25FTAwayProb()) / 2;
            double un15FTProb = (partita.getHomeTeam().getUn15FTHomeProb() + partita.getAwayTeam().getUn15FTAwayProb()) / 2;
            double un25FTProb = (partita.getHomeTeam().getUn25FTHomeProb() + partita.getAwayTeam().getUn25FTAwayProb()) / 2;
            double GGFTProb = (partita.getHomeTeam().getGGFTHomeProb() + partita.getAwayTeam().getGGFTAwayProb()) / 2;
            double NGFTProb = (partita.getHomeTeam().getNGFTHomeProb() + partita.getAwayTeam().getNGFTAwayProb()) / 2;

            partita.setXHTProb(XHTProb);
            partita.setXFTProb(XFTProb);
            partita.setOv05HTProb(ov05HTProb);
            partita.setOv15HTProb(ov15HTProb);
            partita.setUn05HTProb(un05HTProb);
            partita.setUn15HTProb(un15HTProb);
            partita.setGGHTProb(GGHTProb);
            partita.setGGFTProb(GGFTProb);
            partita.setOv25FTProb(ov25FTProb);
            partita.setUn15FTProb(un15FTProb);
            partita.setUn25FTProb(un25FTProb);
            partita.setHTFTXXProb(HTFTXXProb);
            partita.setNGFTProb(NGFTProb);

        }

        return listaPartiteOggi;
    }

    // legge i csv sorgenti e crea una lista delle partite con i risultati
    public ArrayList<Match> creaListaPartiteGiocate() {
        ArrayList<Match> listaPartite = new ArrayList<>();

        for (File cartella : Objects.requireNonNull(csv.listFiles())) {
                try {
                    CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(cartella.getAbsoluteFile())));

                    // comincia a leggere dalla seconda riga, saltando l'header
                    String[] elementi = csvReader.readNext();
                    elementi = csvReader.readNext();

                    // seleziona gli elementi e crea una lista di tutte le partite
                    int idx = 0;
                    while (elementi != null) {
                        String div = elementi[idx++];
                        Squadra homeTeam = new Squadra();
                        homeTeam.setName(elementi[idx++]);
                        Squadra awayTeam = new Squadra();
                        awayTeam.setName(elementi[idx++]);
                        String fthg = elementi[idx++];
                        String ftag = elementi[idx++];
                        String ftr = elementi[idx++];
                        String hthg = elementi[idx++];
                        String htag = elementi[idx++];
                        String htr = elementi[idx++];
                        Match match = new Match(div, homeTeam, awayTeam, fthg, ftag, ftr, hthg, htag, htr);
                        idx = 0;
                        listaPartite.add(match);
                        elementi = csvReader.readNext();
                    }
                    csvReader.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
        }

        // SOSTITUISCE I VALORI ASSENTI DEI GOAL CON 0(PARTITE A TAVOLINO)
        for (Match match : listaPartite) {
            if (match.getHthg().equals("") || match.getHtag().equals("")) {
                match.setHthg("0");
                match.setHtr("D");
                match.setHtag("0");
                if (match.getFthg().equals("") || match.getFtag().equals("")) {
                    match.setFthg("0");
                    match.setFtag("0");
                    match.setFtr("D");
                }
            } else if (match.getFthg().equals("") || match.getFtag().equals("")) {
                match.setFthg("0");
                match.setFtag("0");
                match.setFtr("D");
            }
        }
        return listaPartite;
    }

    // legge la lista delle partite e crea una lista di tutte le squadre
    public ArrayList<Squadra> creaListaSquadre() {
        ArrayList<Match> listaPartite = creaListaPartiteGiocate();
        ArrayList<Squadra> listaSquadre = new ArrayList<>();

        // crea una lista di tutte le squadre analizzate
        for (Match match : listaPartite) {
            listaSquadre.add(new Squadra(match.getHomeTeam().getName(), match.getDiv()));
        }

        // elimina tutti i duplicati
        for (int i = 0; i < listaSquadre.size(); i++) {
            for (int j = listaSquadre.size() - 1; j > i; j--) {
                if (listaSquadre.get(j).getName().equals(listaSquadre.get(i).getName())) {
                    listaSquadre.remove(listaSquadre.get(j));
                }
            }
        }
        System.out.println("Squadre da analizzare: " + listaSquadre.size());

        return listaSquadre;
    }

    // crea una lista dei link sorgenti e sigle dei campionati
    public ArrayList<String> creaListaLinkSorgenti13() {
        File file = new File("res\\file_creati\\Link sorgenti\\link_sorgenti_soccerstats.txt");
        ArrayList<String> linkSorgentiSoccerstats = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                String[] elements = line.split(",");
                linkSorgentiSoccerstats.add(elements[0]);
                line = br.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return linkSorgentiSoccerstats;
    }

    public Map<String, String> creaSigleCampionati() {
        Map<String, String> sigleCampionati = new HashMap<>();
        File file = new File("res\\file_creati\\Link sorgenti\\link_sorgenti_soccerstats.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                String[] elements = line.split(",");
                String siglaEstesa = elements[0].substring(elements[0].indexOf("=") + 1, elements[0].indexOf("&"));
                sigleCampionati.put(siglaEstesa, elements[1]);
                line = br.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sigleCampionati;
    }

    // assegna sigla campionato
    public String trovaSigla(String link) {
        Map<String, String> sigleCampionati = creaSigleCampionati();
        String sigla = "";
        String siglaEstesa = link.substring(link.indexOf("=") + 1, link.indexOf("&"));
        for (int i = 0; i < sigleCampionati.size(); i++) {
            if (sigleCampionati.containsKey(siglaEstesa)) {
                sigla = sigleCampionati.get(siglaEstesa);
            }
        }
        return sigla;
    }

    // crea una lista di elementi della pagina web
    public ArrayList<String> leggiHTMLGet13(String link) {
        ArrayList<String> elementiPerCampionato = new ArrayList<>();
        Document doc;
        try {
            doc = Jsoup.connect(link).get();

            Elements tableElements = doc.getElementsByTag("table");
            if (!tableElements.isEmpty()) {
                Element tableElement = tableElements.get(13);
                Elements rowElements = tableElement.getElementsByTag("tr");
                if (!rowElements.isEmpty()) {
                    for (Element rowElement : rowElements) {
                        Elements dElements = rowElement.getElementsByTag("td");
                        if (!dElements.isEmpty()) {
                            for (Element dElement : dElements) {
                                elementiPerCampionato.add(dElement.text());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementiPerCampionato;
    }

    // genera Htr o Ftr
    public String generaR(String hg, String ag) {
        String result = "";
        if (Integer.parseInt(hg) > Integer.parseInt(ag)) {
            result = "H";
        } else if (Integer.parseInt(hg) < Integer.parseInt(ag)) {
            result = "A";
        } else {
            result = "D";
        }

        return result;
    }

    // confronta la data del match con quella del momento
    public boolean daGiocare(String data, String ora) {
        String[] elementiData = data.split(" ");
        int mese = 0;
        boolean daGiocare = false;
        switch (elementiData[2]) {
            case "Jan" -> mese = 1;
            case "Feb" -> mese = 2;
            case "Mar" -> mese = 3;
            case "Apr" -> mese = 4;
            case "May" -> mese = 5;
            case "Jun" -> mese = 6;
            case "Jul" -> mese = 7;
            case "Aug" -> mese = 8;
            case "Sep" -> mese = 9;
            case "Oct" -> mese = 10;
            case "Nov" -> mese = 11;
            case "Dec" -> mese = 12;
        }

        String[] elementiOra = ora.split(":");

        LocalDateTime dataPartita = LocalDateTime.of(LocalDateTime.now().getYear(), mese, Integer.parseInt(elementiData[1]), Integer.parseInt(elementiOra[0]), Integer.parseInt(elementiOra[1]));
        LocalDateTime domani = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(9, 0));

        if (dataPartita.isBefore(domani)) {
            daGiocare = true;
        }
        return daGiocare;
    }

    // cerca la squadra nella lista e la assegna al match
    public Squadra getSquadraByName(String name, ArrayList<Squadra> listaSquadreAggiornata) {
        Squadra squadraReturn = new Squadra();
        for (Squadra squadra : listaSquadreAggiornata) {
            if (squadra.getName().equals(name)) {
                squadraReturn = squadra;
            }
        }
        return squadraReturn;
    }

    public boolean scaricareSorgenti(File fileName) {
        boolean scarica = false;
        try{
            BasicFileAttributes attr = Files.readAttributes(Paths.get(fileName.getPath()), BasicFileAttributes.class);
            LocalDateTime lastModifiedTime = LocalDateTime.ofInstant(attr.lastModifiedTime().toInstant(), ZoneId.systemDefault());
            if (lastModifiedTime.getDayOfMonth() != LocalDate.now().getDayOfMonth()){
                scarica = true;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return scarica;
    }
}
