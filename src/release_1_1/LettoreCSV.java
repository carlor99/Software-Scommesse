package release_1_1;

import com.opencsv.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LettoreCSV {
    ScrapperCSVCreator csvCreator = new ScrapperCSVCreator();
    ArrayList<Squadra> listaSquadre = csvCreator.creaListaSquadre();
    ArrayList<Match> listaPartite = csvCreator.creaListaPartiteGiocate();


    public LettoreCSV(){
        aggiornaPartiteDisputate();
        aggiornaX();
        aggiornaOver();
        aggiornaHTFTXX();
        aggiornaUnder();
        aggiornaGGNG();
        creaFileStatistiche();
        creaFileMatchDaGiocare();
    }

    public void aggiornaPartiteDisputate(){
        // aggiorna le partite disputate da ogni singola squadra durante l'anno
        int partiteDisputateCasa = 0;
        int partiteDisputateTrasferta = 0;
        for (Squadra squadra : listaSquadre){
            for (Match match : listaPartite){
                if(match.getHomeTeam().getName().equals(squadra.getName())){
                    partiteDisputateCasa++;
                } else if(match.getAwayTeam().getName().equals(squadra.getName())){
                    partiteDisputateTrasferta++;
                }
            }
            squadra.setHomeMatches(partiteDisputateCasa);
            squadra.setAwayMatches(partiteDisputateTrasferta);
            partiteDisputateCasa = 0;
            partiteDisputateTrasferta = 0;
        }
    }

    public void aggiornaX(){
        ArrayList<Match> listaXHT = new ArrayList<>();
        ArrayList<Match> listaXFT = new ArrayList<>();

        // filtra le partite per X HT e FT
        for (Match match : listaPartite){
            if (match.getHtr().equals("D")){
                listaXHT.add(match);
            }
            if(match.getFtr().equals("D")){
                listaXFT.add(match);
            }
        }

        System.out.println("X HT: " + listaXHT.size());
        System.out.println("X FT: " + listaXFT.size());

        // aggiorna il numero di x ht delle squadre
        int countCasa = 0, countTrasferta = 0;
        for (Squadra squadra : listaSquadre){
            for (Match match : listaXHT){
                if(match.getHomeTeam().getName().equals(squadra.getName())){
                    countCasa++;
                }else if(match.getAwayTeam().getName().equals(squadra.getName())){
                    countTrasferta++;
                }
            }
            squadra.setXHTHome(countCasa);
            squadra.setXHTAway(countTrasferta);
            countCasa = 0;
            countTrasferta = 0;
        }

        // aggiorna il numero di x ft delle squadre
        for (Squadra squadra : listaSquadre){
            for (Match match : listaXFT){
                if(match.getHomeTeam().getName().equals(squadra.getName())){
                    countCasa++;
                }else if(match.getAwayTeam().getName().equals(squadra.getName())){
                    countTrasferta++;
                }
            }
            squadra.setXFTHome(countCasa);
            squadra.setXFTAway(countTrasferta);
            countCasa = 0;
            countTrasferta = 0;
        }       

        // assegna le probabilità alle squadre
        for (Squadra squadra : listaSquadre){
            double XHTHomeProb = (double)squadra.getXHTHome()/squadra.getHomeMatches()*100;
            double XHTAwayProb = (double)squadra.getXHTAway()/squadra.getAwayMatches()*100;
            squadra.setXHTHomeProb(XHTHomeProb);
            squadra.setXHTAwayProb(XHTAwayProb);

            double XFTHomeProb = (double)squadra.getXFTHome()/squadra.getHomeMatches()*100;
            double XFTAwayProb = (double)squadra.getXFTAway()/squadra.getAwayMatches()*100;
            squadra.setXFTHomeProb(XFTHomeProb);
            squadra.setXFTAwayProb(XFTAwayProb);
        }
    }

    public void aggiornaOver(){
        ArrayList<Match> listaOv05HT = new ArrayList<>();
        ArrayList<Match> listaOv15HT = new ArrayList<>();
        ArrayList<Match> listaOv25FT = new ArrayList<>();

        // crea una lista di partite in cui c'è ov ht
        for (Match match : listaPartite) {
            int sommaGoalHT = Integer.parseInt(match.getHthg()) + Integer.parseInt(match.getHtag());
            int sommaGoalFT = Integer.parseInt(match.getFthg()) + Integer.parseInt(match.getFtag());

            // ov 0.5 o 1.5 ht
            if (sommaGoalHT > 0){
                listaOv05HT.add(match);
            }
            if(sommaGoalHT > 1) {
                listaOv15HT.add(match);
            }
            
            // ov 2.5 o 3.5 ft
            if (sommaGoalFT > 3){
                listaOv25FT.add(match);
            }
        }

        System.out.println("OV 0.5 HT: " + listaOv05HT.size());
        System.out.println("OV 1.5 HT: " + listaOv15HT.size());
        System.out.println("OV 2.5 FT: " + listaOv25FT.size());


        // aggiorna il numero di ov 0.5 ht delle squadre
        int countCasa = 0, countTrasferta = 0;
        for (Squadra squadra : listaSquadre){
            for (Match match : listaOv05HT){
                if(match.getHomeTeam().getName().equals(squadra.getName())){
                    countCasa++;
                }else if(match.getAwayTeam().getName().equals(squadra.getName())){
                    countTrasferta++;
                }
            }
            squadra.setOv05HTHome(countCasa);
            squadra.setOv05HTAway(countTrasferta);
            countCasa = 0;
            countTrasferta = 0;
        }
        
        // aggiorna il numero di ov 1.5 ht delle squadre
        countCasa = 0; countTrasferta = 0;
        for (Squadra squadra : listaSquadre){
            for (Match match : listaOv15HT){
                if(match.getHomeTeam().getName().equals(squadra.getName())){
                    countCasa++;
                }else if(match.getAwayTeam().getName().equals(squadra.getName())){
                    countTrasferta++;
                }
            }
            squadra.setOv15HTHome(countCasa);
            squadra.setOv15HTAway(countTrasferta);
            countCasa = 0;
            countTrasferta = 0;
        }
        
        // aggiorna il numero di ov 2.5 ft delle squadre
        countCasa = 0; countTrasferta = 0;
        for (Squadra squadra : listaSquadre){
            for (Match match : listaOv25FT){
                if(match.getHomeTeam().getName().equals(squadra.getName())){
                    countCasa++;
                }else if(match.getAwayTeam().getName().equals(squadra.getName())){
                    countTrasferta++;
                }
            }
            squadra.setOv25FTHome(countCasa);
            squadra.setOv25FTAway(countTrasferta);
            countCasa = 0;
            countTrasferta = 0;
        }

        // assegna le probabilità alle squadre
        for (Squadra squadra : listaSquadre){
            double ov05HTHomeProb = (double)squadra.getOv05HTHome()/squadra.getHomeMatches()*100;
            double ov05HTAwayProb = (double)squadra.getOv05HTAway()/squadra.getAwayMatches()*100;
            squadra.setOv05HTHomeProb(ov05HTHomeProb);
            squadra.setOv05HTAwayProb(ov05HTAwayProb);

            double ov15HTHomeProb = (double)squadra.getOv15HTHome()/squadra.getHomeMatches()*100;
            double ov15HTAwayProb = (double)squadra.getOv15HTAway()/squadra.getAwayMatches()*100;
            squadra.setOv15HTHomeProb(ov15HTHomeProb);
            squadra.setOv15HTAwayProb(ov15HTAwayProb);

            double ov25FTHomeProb = (double)squadra.getOv25FTHome()/squadra.getHomeMatches()*100;
            double ov25FTAwayProb = (double)squadra.getOv25FTAway()/squadra.getAwayMatches()*100;
            squadra.setOv25FTHomeProb(ov25FTHomeProb);
            squadra.setOv25FTAwayProb(ov25FTAwayProb);
        }
    }
    
    public void aggiornaUnder(){
        ArrayList<Match> listaUn05HT = new ArrayList<>();
        ArrayList<Match> listaUn15HT = new ArrayList<>();
        ArrayList<Match> listaUn15FT = new ArrayList<>();
        ArrayList<Match> listaUn25FT = new ArrayList<>();

        // crea una lista di partite in cui c'è under ht o ft
        for (Match match : listaPartite) {
            int sommaGoalHT = Integer.parseInt(match.getHthg()) + Integer.parseInt(match.getHtag());
            int sommaGoalFT = Integer.parseInt(match.getFthg()) + Integer.parseInt(match.getFtag());

            // un 0.5 o 1.5 ht
            if (sommaGoalHT < 2){
                listaUn15HT.add(match);
            }
            if(sommaGoalHT < 1) {
                listaUn05HT.add(match);
            }

            // un 1.5 o 2.5 ft
            if (sommaGoalFT < 3){
                listaUn25FT.add(match);
            }
            if(sommaGoalFT < 2){
                listaUn15FT.add(match);
            }
        }

        System.out.println("UN 0.5 HT: " + listaUn05HT.size());
        System.out.println("UN 1.5 HT: " + listaUn15HT.size());
        System.out.println("UN 1.5 FT: " + listaUn15FT.size());
        System.out.println("UN 2.5 FT: " + listaUn25FT.size());


        // aggiorna il numero di un 0.5 ht delle squadre
        int countCasa = 0, countTrasferta = 0;
        for (Squadra squadra : listaSquadre){
            for (Match match : listaUn05HT){
                if(match.getHomeTeam().getName().equals(squadra.getName())){
                    countCasa++;
                }else if(match.getAwayTeam().getName().equals(squadra.getName())){
                    countTrasferta++;
                }
            }
            squadra.setUn05HTHome(countCasa);
            squadra.setUn05HTAway(countTrasferta);
            countCasa = 0;
            countTrasferta = 0;
        }

        // aggiorna il numero di un 1.5 ht delle squadre
        countCasa = 0; countTrasferta = 0;
        for (Squadra squadra : listaSquadre){
            for (Match match : listaUn15HT){
                if(match.getHomeTeam().getName().equals(squadra.getName())){
                    countCasa++;
                }else if(match.getAwayTeam().getName().equals(squadra.getName())){
                    countTrasferta++;
                }
            }
            squadra.setUn15HTHome(countCasa);
            squadra.setUn15HTAway(countTrasferta);
            countCasa = 0;
            countTrasferta = 0;
        }

        // aggiorna il numero di un 1.5 ft delle squadre
        countCasa = 0; countTrasferta = 0;
        for (Squadra squadra : listaSquadre){
            for (Match match : listaUn15FT){
                if(match.getHomeTeam().getName().equals(squadra.getName())){
                    countCasa++;
                }else if(match.getAwayTeam().getName().equals(squadra.getName())){
                    countTrasferta++;
                }
            }
            squadra.setUn15FTHome(countCasa);
            squadra.setUn15FTAway(countTrasferta);
            countCasa = 0;
            countTrasferta = 0;
        }

        // aggiorna il numero di un 2.5 ft delle squadre
        countCasa = 0; countTrasferta = 0;
        for (Squadra squadra : listaSquadre){
            for (Match match : listaUn25FT){
                if(match.getHomeTeam().getName().equals(squadra.getName())){
                    countCasa++;
                }else if(match.getAwayTeam().getName().equals(squadra.getName())){
                    countTrasferta++;
                }
            }
            squadra.setUn25FTHome(countCasa);
            squadra.setUn25FTAway(countTrasferta);
            countCasa = 0;
            countTrasferta = 0;
        }
        
        // aggiorna le probabilità delle squadre
        for (Squadra squadra : listaSquadre){
            double un05HTHomeProb = (double)squadra.getUn05HTHome()/squadra.getHomeMatches()*100;
            double un05HTAwayProb = (double)squadra.getUn05HTAway()/squadra.getAwayMatches()*100;
            squadra.setUn05HTHomeProb(un05HTHomeProb);
            squadra.setUn05HTAwayProb(un05HTAwayProb);

            double un15HTHomeProb = (double)squadra.getUn15HTHome()/squadra.getHomeMatches()*100;
            double un15HTAwayProb = (double)squadra.getUn15HTAway()/squadra.getAwayMatches()*100;
            squadra.setUn15HTHomeProb(un15HTHomeProb);
            squadra.setUn15HTAwayProb(un15HTAwayProb);

            double un15FTHomeProb = (double)squadra.getUn15FTHome()/squadra.getHomeMatches()*100;
            double un15FTAwayProb = (double)squadra.getUn15FTAway()/squadra.getAwayMatches()*100;
            squadra.setUn15FTHomeProb(un15FTHomeProb);
            squadra.setUn15FTAwayProb(un15FTAwayProb);

            double un25FTHomeProb = (double)squadra.getUn25FTHome()/squadra.getHomeMatches()*100;
            double un25FTAwayProb = (double)squadra.getUn25FTAway()/squadra.getAwayMatches()*100;
            squadra.setUn25FTHomeProb(un25FTHomeProb);
            squadra.setUn25FTAwayProb(un25FTAwayProb);
        }
    }

    public void aggiornaHTFTXX(){
        ArrayList<Match> listaxx = new ArrayList<>();

        // crea una lista di partite in cui c'è x/x
        for (Match match : listaPartite){
            if(match.getHtr().equals("D") && match.getFtr().equals("D")){
                    listaxx.add(match);
                }
            }

        System.out.println("X/X: " + listaxx.size());        

        // aggiorna il numero di X/X delle squadre
        int countCasa = 0, countTrasferta = 0;
        for (Squadra squadra : listaSquadre){
            for (Match match : listaxx){
                if(match.getHomeTeam().getName().equals(squadra.getName())){
                    countCasa++;
                }else if(match.getAwayTeam().getName().equals(squadra.getName())){
                    countTrasferta++;
                }
            }
            squadra.setHTFTXXHome(countCasa);
            squadra.setHTFTXXAway(countTrasferta);
            countCasa = 0;
            countTrasferta = 0;
        }

        // assegna le probabilità alle squadre
        for (Squadra squadra : listaSquadre){
            double ribxxHomeProb = (double)squadra.getHTFTXXHome()/squadra.getHomeMatches()*100;
            double ribxxAwayProb = (double)squadra.getHTFTXXAway()/squadra.getAwayMatches()*100;
            squadra.setHTFTXXHomeProb(ribxxHomeProb);
            squadra.setHTFTXXAwayProb(ribxxAwayProb);
        }
    }

    public void aggiornaGGNG(){
        ArrayList<Match> listaGGHT = new ArrayList<>();
        ArrayList<Match> listaGGFT = new ArrayList<>();
        ArrayList<Match> listaNGFT = new ArrayList<>();


        for (Match match : listaPartite) {
            // gg ht o ft/ ng
            if ((Integer.parseInt(match.getHthg()) > 0) && (Integer.parseInt(match.getHtag()) > 0)){
                listaGGHT.add(match);
            }
            if((Integer.parseInt(match.getFthg()) > 0) && (Integer.parseInt(match.getFtag()) > 0)) {
                listaGGFT.add(match);
            } else {listaNGFT.add(match);}
        }

        // aggiorna il numero di gg ht delle squadre
        int countCasa = 0, countTrasferta = 0;
        for (Squadra squadra : listaSquadre){
            for (Match match : listaGGHT){
                if(match.getHomeTeam().getName().equals(squadra.getName())){
                    countCasa++;
                }else if(match.getAwayTeam().getName().equals(squadra.getName())){
                    countTrasferta++;
                }
            }
            squadra.setGGHTHome(countCasa);
            squadra.setGGHTAway(countTrasferta);
            countCasa = 0;
            countTrasferta = 0;
        }

        // aggiorna il numero di gg ft delle squadre
        countCasa = 0; countTrasferta = 0;
        for (Squadra squadra : listaSquadre){
            for (Match match : listaGGFT){
                if(match.getHomeTeam().getName().equals(squadra.getName())){
                    countCasa++;
                }else if(match.getAwayTeam().getName().equals(squadra.getName())){
                    countTrasferta++;
                }
            }
            squadra.setGGFTHome(countCasa);
            squadra.setGGFTAway(countTrasferta);
            squadra.setNGFTHome(squadra.getHomeMatches() - countCasa);
            squadra.setNGFTAway(squadra.getAwayMatches() - countTrasferta);
            countCasa = 0;
            countTrasferta = 0;
        }

        // assegna le probabilità alle squadre
        for (Squadra squadra : listaSquadre){
            double GGHTHomeProb = (double)squadra.getGGHTHome()/squadra.getHomeMatches()*100;
            double GGHTAwayProb = (double)squadra.getGGHTAway()/squadra.getAwayMatches()*100;
            squadra.setGGHTHomeProb(GGHTHomeProb);
            squadra.setGGHTAwayProb(GGHTAwayProb);

            double GGFTHomeProb = (double)squadra.getGGFTHome()/squadra.getHomeMatches()*100;
            double GGFTAwayProb = (double)squadra.getGGFTAway()/squadra.getAwayMatches()*100;
            squadra.setGGFTHomeProb(GGFTHomeProb);
            squadra.setGGFTAwayProb(GGFTAwayProb);

            double NGFTHomeProb = (double)squadra.getNGFTHome()/squadra.getHomeMatches()*100;
            double NGFTAwayProb = (double)squadra.getNGFTAway()/squadra.getAwayMatches()*100;
            squadra.setNGFTHomeProb(NGFTHomeProb);
            squadra.setNGFTAwayProb(NGFTAwayProb);
        }
    }

    public void creaFileStatistiche(){
        File csvPartite = new File("res\\file_creati\\Today Matches.csv");
        ArrayList<Match> listaPartiteOggi = csvCreator.creaListaPartiteDaGiocare(listaSquadre);

        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(csvPartite));
            CSVWriter csvWriter = new CSVWriter(bw,CSVWriter.DEFAULT_SEPARATOR,CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.DEFAULT_ESCAPE_CHARACTER,CSVWriter.DEFAULT_LINE_END);
            String[] header = {"LEAGUE","TIME","HOME","AWAY","X HT","X FT","OV 0.5 HT ","OV 1.5 HT","UN 0.5 HT","UN 1.5 HT","GG HT","X/X","OV 2.5 FT","UN 1.5 FT ","UN 2.5 FT","GG FT","NG FT"};
            csvWriter.writeNext(header);
            int count = 0;
            for (Match match : listaPartiteOggi){
                String league = match.getDiv();
                String time = match.getTime();
                String home = match.getHomeTeam().getName();
                String away = match.getAwayTeam().getName();
                String XHT = String.valueOf(match.getXHTProb());
                String XFT = String.valueOf(match.getXFTProb());
                String OV05HT = String.valueOf(match.getOv05HTProb());
                String OV15HT = String.valueOf(match.getOv15HTProb());
                String UN05HT = String.valueOf(match.getUn05HTProb());
                String UN15HT = String.valueOf(match.getUn15HTProb());
                String GGHT = String.valueOf(match.getGGHTProb());
                String HTFTXX = String.valueOf(match.getHTFTXXProb());
                String OV25FT = String.valueOf(match.getOv25FTProb());
                String UN15FT = String.valueOf(match.getUn15FTProb());
                String UN25FT = String.valueOf(match.getUn25FTProb());
                String GGFT = String.valueOf(match.getGGFTProb());
                String NGFT = String.valueOf(match.getNGFTProb());
                csvWriter.writeNext(new String[]{league,time,home,away,XHT,XFT,OV05HT,OV15HT,UN05HT,UN15HT,GGHT,HTFTXX,OV25FT,UN15FT,UN25FT,GGFT,NGFT});
                count++;
            }
            csvWriter.close();
            System.out.println("File Today Matches.csv creato con " + count + " righe");
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void creaFileMatchDaGiocare(){
        try{
            // "LEAGUE","TIME","HOME","AWAY","X HT","X FT","OV 0.5 HT ","OV 1.5 HT","UN 0.5 HT","UN 1.5 HT","GG HT","X/X","OV 2.5 FT","UN 1.5 FT ","UN 2.5 FT","GG FT","NG FT"
            // LEAGUE,DATE,TIME,HOME,AWAY,TIP 1,PROB1,USCITO1,TIP 2,PROB2,USCITO12,TIP 3,PROB3,USCITO13,TIP 4,PROB4,USCITO14
            CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader("res\\file_creati/Today Matches.csv")));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String data = LocalDate.now().format(formatter);
            BufferedWriter bw = new BufferedWriter(new FileWriter("res\\file_creati/Pronostici partite/Pronostici partite oggi.csv"));
            CSVWriter csvWriter = new CSVWriter(bw,CSVWriter.DEFAULT_SEPARATOR,CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.DEFAULT_ESCAPE_CHARACTER,CSVWriter.DEFAULT_LINE_END);
            String[] header = csvReader.readNext();
            String[] match = csvReader.readNext();
            int count = 0;
            csvWriter.writeNext(new String[]{"LEAGUE","DATE","TIME","HOME","AWAY","TIP 1","PROB1","USCITO1","TIP 2","PROB2","USCITO12","TIP 3","PROB3","USCITO13","TIP 4","PROB4","USCITO14"});
            while(match != null){
                String temp = match[0] + "," + data + "," + match[1] + "," + match[2] + "," + match[3] + ",";
                for (int i=4;i< match.length;i++){
                    if(Double.parseDouble(match[i]) >= 70.0){
                        String valore = String.format("%.4f",Double.parseDouble(match[i])/100.00).replace(',','.');
                        temp += header[i] + "," + valore + ",,";
                    }
                }
                String[] rigaDaScrivere = temp.split(",");
                if(rigaDaScrivere.length > 5) {
                    csvWriter.writeNext(rigaDaScrivere);
                    count++;
                }
                match = csvReader.readNext();
            }
            System.out.println("File Pronostici partite oggi.csv creato con " + count + " righe");
            csvWriter.close();
            bw.close();
            csvReader.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}