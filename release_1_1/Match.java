package release_1_1;

public class Match {
    private String div,ftr,htr,fthg,ftag,hthg,htag,date,time;
    private Squadra homeTeam,awayTeam;
    private double XHTProb,ov05HTProb,ov15HTProb,un05HTProb,un15HTProb,GGHTProb,HTFTXXProb,ov25FTProb,un15FTProb,un25FTProb,GGFTProb,NGFTProb,XFTProb;

    public Match(){}

    public Match(String div, Squadra homeTeam, Squadra awayTeam, String fthg, String ftag, String ftr, String hthg, String htag, String htr){
        this.div = div;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.fthg = fthg;
        this.ftag = ftag;
        this.ftr = ftr;
        this.hthg = hthg;
        this.htag = htag;
        this.htr = htr;
    }

    public String getInfo(){
        return div + " " + homeTeam.getName() + " - " + awayTeam.getName() + " FT: " + fthg + "-" + ftag + " | HT: " + hthg + "-" + htag;
    }

    public String getInfoHT(){
        return div + " " + homeTeam.toString() + " - " + awayTeam.toString() + " HT: " + hthg + "-" + htag;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setawayTeam(Squadra awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setDiv(String div) {
        this.div = div;
    }

    public void setFtag(String ftag) {
        this.ftag = ftag;
    }

    public void setFthg(String fthg) {
        this.fthg = fthg;
    }

    public void setFtr(String ftr) {
        this.ftr = ftr;
    }

    public void sethomeTeam(Squadra homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setHtag(String htag) {
        this.htag = htag;
    }

    public void setHthg(String hthg) {
        this.hthg = hthg;
    }

    public void setHtr(String htr) {
        this.htr = htr;
    }

    public String getDiv() {
        return div;
    }

    public String getFtag() {
        return ftag;
    }

    public String getFthg() {
        return fthg;
    }

    public String getFtr() {
        return ftr;
    }

    public String getHtag() {
        return htag;
    }

    public String getHthg() {
        return hthg;
    }

    public String getHtr() {
        return htr;
    }

    public double getHTFTXXProb() {
        return HTFTXXProb;
    }

    public double getOv05HTProb() {
        return ov05HTProb;
    }

    public double getXHTProb() {
        return XHTProb;
    }

    public Squadra getAwayTeam() {
        return awayTeam;
    }

    public Squadra getHomeTeam() {
        return homeTeam;
    }

    public double getGGFTProb() {
        return GGFTProb;
    }

    public double getOv15HTProb() {
        return ov15HTProb;
    }

    public double getOv25FTProb() {
        return ov25FTProb;
    }

    public double getUn05HTProb() {
        return un05HTProb;
    }

    public double getUn15HTProb() {
        return un15HTProb;
    }

    public double getUn15FTProb() {
        return un15FTProb;
    }

    public double getUn25FTProb() {
        return un25FTProb;
    }

    public double getXFTProb() {
        return XFTProb;
    }

    public void setGGFTProb(double GGFTProb) {
        this.GGFTProb = GGFTProb;
    }

    public void setOv15HTProb(double ov15HTProb) {
        this.ov15HTProb = ov15HTProb;
    }

    public void setOv25FTProb(double ov25FTProb) {
        this.ov25FTProb = ov25FTProb;
    }

    public void setUn05HTProb(double un05HTProb) {
        this.un05HTProb = un05HTProb;
    }

    public void setUn15HTProb(double un15HTProb) {
        this.un15HTProb = un15HTProb;
    }

    public void setUn25FTProb(double un25FTProb) {
        this.un25FTProb = un25FTProb;
    }

    public void setUn15FTProb(double un15FTProb) {
        this.un15FTProb = un15FTProb;
    }

    public void setXFTProb(double XFTProb) {
        this.XFTProb = XFTProb;
    }

    public void setHomeTeam(Squadra homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(Squadra awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setHTFTXXProb(double HTFTXXProb) {
        this.HTFTXXProb = HTFTXXProb;
    }

    public void setOv05HTProb(double ov05HTProb) {
        this.ov05HTProb = ov05HTProb;
    }

    public void setXHTProb(double XHTProb) {
        this.XHTProb = XHTProb;
    }

    public double getGGHTProb() {
        return GGHTProb;
    }

    public void setGGHTProb(double GGHTProb) {
        this.GGHTProb = GGHTProb;
    }

    public double getNGFTProb() {
        return NGFTProb;
    }

    public void setNGFTProb(double NGFTProb) {
        this.NGFTProb = NGFTProb;
    }
}