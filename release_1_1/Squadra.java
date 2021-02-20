package release_1_1;

public class Squadra {
    private String name,league;
    private int homeMatches,XHTHome,ov05HTHome,ov15HTHome,un05HTHome,un15HTHome,GGHTHome,HTFTXXHome,ov25FTHome,un15FTHome,un25FTHome,GGFTHome,NGFTHome,XFTHome;
    private int awayMatches,XHTAway,ov05HTAway,ov15HTAway,un05HTAway,un15HTAway,GGHTAway,HTFTXXAway,ov25FTAway,un15FTAway,un25FTAway,GGFTAway,NGFTAway,XFTAway;
    private double XHTHomeProb,ov05HTHomeProb,ov15HTHomeProb,un05HTHomeProb,un15HTHomeProb,GGHTHomeProb,HTFTXXHomeProb,ov25FTHomeProb,un15FTHomeProb,un25FTHomeProb,GGFTHomeProb,NGFTHomeProb,XFTHomeProb;
    private double XHTAwayProb,ov05HTAwayProb,ov15HTAwayProb,un05HTAwayProb,un15HTAwayProb,GGHTAwayProb,HTFTXXAwayProb,ov25FTAwayProb,un15FTAwayProb,un25FTAwayProb,GGFTAwayProb,NGFTAwayProb,XFTAwayProb;

    public Squadra(){}

    public Squadra(String name,String league){
        this.name = name;
        this.league = league;
    }

    public String[] getInfoPerCsv(){
        // "LEAGUE","NAME","X HT HOME","X HT AWAY","X FT HOME","X FT AWAY","OV 0.5 HT HOME","OV 0.5 HT AWAY","OV 1.5 HT HOME","OV 1.5 HT AWAY","UN 0.5 HT HOME","UN 0.5 HT AWAY","UN 1.5 HT HOME","UN 1.5 HT AWAY","GG HT HOME","GG HT AWAY","X/X HOME","X/X AWAY","OV 2.5 FT HOME","OV 2.5 FT AWAY","UN 1.5 FT HOME","UN 1.5 FT AWAY","UN 2.5 FT HOME","UN 2.5 FT AWAY","GG FT HOME","GG FT AWAY","NG FT HOME","NG FT AWAY"
        return new String[]{league,name,String.valueOf(XHTHomeProb),String.valueOf(XHTAwayProb),String.valueOf(XFTHomeProb),String.valueOf(XFTAwayProb),String.valueOf(ov05HTHomeProb),String.valueOf(ov05HTAwayProb),String.valueOf(ov15HTHomeProb),
                String.valueOf(ov15HTAwayProb),String.valueOf(un05HTHomeProb),String.valueOf(un05HTAwayProb),String.valueOf(un15HTHomeProb),String.valueOf(un15HTAwayProb)
                ,String.valueOf(GGHTHomeProb),String.valueOf(GGHTAwayProb),String.valueOf(HTFTXXHomeProb),String.valueOf(HTFTXXAwayProb),String.valueOf(ov25FTHomeProb)
                ,String.valueOf(ov25FTAwayProb),String.valueOf(un15FTHomeProb),String.valueOf(un15FTAwayProb)
                ,String.valueOf(un25FTHomeProb),String.valueOf(un25FTAwayProb),String.valueOf(GGFTHomeProb),String.valueOf(GGFTAwayProb),String.valueOf(NGFTHomeProb),String.valueOf(NGFTAwayProb)};
    }

    public double getHTFTXXAwayProb() {
        return HTFTXXAwayProb;
    }

    public double getHTFTXXHomeProb() {
        return HTFTXXHomeProb;
    }

    public void setHTFTXXAwayProb(double HTFTXXAwayProb) {
        this.HTFTXXAwayProb = HTFTXXAwayProb;
    }

    public void setHTFTXXHomeProb(double HTFTXXHomeProb) {
        this.HTFTXXHomeProb = HTFTXXHomeProb;
    }

    public int getHTFTXXAway() {
        return HTFTXXAway;
    }

    public int getHTFTXXHome() {
        return HTFTXXHome;
    }

    public void setHTFTXXHome(int HTFTXXHome) {
        this.HTFTXXHome = HTFTXXHome;
    }

    public void setHTFTXXAway(int HTFTXXAway) {
        this.HTFTXXAway = HTFTXXAway;
    }

    public int getov05HTHome() {
        return ov05HTHome;
    }

    public double getov05HTHomeProb() {
        return ov05HTHomeProb;
    }

    public double getov05HTAwayProb() {
        return ov05HTAwayProb;
    }

    public int getov05HTAway() {
        return ov05HTAway;
    }

    public int getHomeMatches() {
        return homeMatches;
    }

    public int getAwayMatches() {
        return awayMatches;
    }

    public double getXHTAwayProb() {
        return XHTAwayProb;
    }

    public double getXHTHomeProb() {
        return XHTHomeProb;
    }

    public int getXHTHome() {
        return XHTHome;
    }

    public int getXHTAway() {
        return XHTAway;
    }

    public void setov05HTHomeProb(double ov05HTHomeProb) {
        this.ov05HTHomeProb = ov05HTHomeProb;
    }

    public void setov05HTHome(int ov05HTHome) {
        this.ov05HTHome = ov05HTHome;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public void setHomeMatches(int homeMatches) {
        this.homeMatches = homeMatches;
    }

    public void setAwayMatches(int awayMatches) {
        this.awayMatches = awayMatches;
    }

    public void setXHTAway(int XHTAway) {
        this.XHTAway = XHTAway;
    }

    public void setov05HTAway(int ov05HTAway) {
        this.ov05HTAway = ov05HTAway;
    }

    public void setov05HTAwayProb(double ov05HTAwayProb) {
        this.ov05HTAwayProb = ov05HTAwayProb;
    }

    public void setXHTAwayProb(double XHTAwayProb) {
        this.XHTAwayProb = XHTAwayProb;
    }

    public void setXHTHome(int XHTHome) {
        this.XHTHome = XHTHome;
    }

    public void setXHTHomeProb(double XHTHomeProb) {
        this.XHTHomeProb = XHTHomeProb;
    }

    public String getLeague() {
        return league;
    }

    public String getName() {
        return name;
    }

    public int getGGFTHome() {
        return GGFTHome;
    }

    public int getOv05HTAway() {
        return ov05HTAway;
    }

    public int getOv05HTHome() {
        return ov05HTHome;
    }

    public int getOv15HTAway() {
        return ov15HTAway;
    }

    public int getOv15HTHome() {
        return ov15HTHome;
    }

    public int getOv25FTAway() {
        return ov25FTAway;
    }

    public int getOv25FTHome() {
        return ov25FTHome;
    }

    public int getGGFTAway() {
        return GGFTAway;
    }

    public double getOv05HTHomeProb() {
        return ov05HTHomeProb;
    }

    public int getUn05HTAway() {
        return un05HTAway;
    }

    public int getUn05HTHome() {
        return un05HTHome;
    }

    public int getUn15FTAway() {
        return un15FTAway;
    }

    public int getUn15FTHome() {
        return un15FTHome;
    }

    public int getUn15HTAway() {
        return un15HTAway;
    }

    public double getOv15HTHomeProb() {
        return ov15HTHomeProb;
    }

    public int getUn15HTHome() {
        return un15HTHome;
    }

    public int getUn25FTAway() {
        return un25FTAway;
    }

    public int getUn25FTHome() {
        return un25FTHome;
    }

    public double getOv25FTHomeProb() {
        return ov25FTHomeProb;
    }

    public double getUn05HTHomeProb() {
        return un05HTHomeProb;
    }

    public double getUn15HTHomeProb() {
        return un15HTHomeProb;
    }

    public double getGGFTHomeProb() {
        return GGFTHomeProb;
    }

    public int getXFTAway() {
        return XFTAway;
    }

    public double getOv05HTAwayProb() {
        return ov05HTAwayProb;
    }

    public int getXFTHome() {
        return XFTHome;
    }

    public double getUn15FTHomeProb() {
        return un15FTHomeProb;
    }

    public double getUn25FTHomeProb() {
        return un25FTHomeProb;
    }

    public double getGGFTAwayProb() {
        return GGFTAwayProb;
    }

    public double getOv15HTAwayProb() {
        return ov15HTAwayProb;
    }

    public double getOv25FTAwayProb() {
        return ov25FTAwayProb;
    }

    public double getUn05HTAwayProb() {
        return un05HTAwayProb;
    }

    public double getUn15FTAwayProb() {
        return un15FTAwayProb;
    }

    public double getUn15HTAwayProb() {
        return un15HTAwayProb;
    }

    public double getUn25FTAwayProb() {
        return un25FTAwayProb;
    }

    public double getXFTAwayProb() {
        return XFTAwayProb;
    }

    public double getXFTHomeProb() {
        return XFTHomeProb;
    }

    public void setGGFTAway(int GGFTAway) {
        this.GGFTAway = GGFTAway;
    }

    public void setGGFTHome(int GGFTHome) {
        this.GGFTHome = GGFTHome;
    }

    public void setOv05HTAway(int ov05HTAway) {
        this.ov05HTAway = ov05HTAway;
    }

    public void setOv05HTHome(int ov05HTHome) {
        this.ov05HTHome = ov05HTHome;
    }

    public void setOv05HTHomeProb(double ov05HTHomeProb) {
        this.ov05HTHomeProb = ov05HTHomeProb;
    }

    public void setOv15HTAway(int ov15HTAway) {
        this.ov15HTAway = ov15HTAway;
    }

    public void setOv15HTHome(int ov15HTHome) {
        this.ov15HTHome = ov15HTHome;
    }

    public void setOv25FTAway(int ov25FTAway) {
        this.ov25FTAway = ov25FTAway;
    }

    public void setOv15HTHomeProb(double ov15HTHomeProb) {
        this.ov15HTHomeProb = ov15HTHomeProb;
    }

    public void setOv25FTHome(int ov25FTHome) {
        this.ov25FTHome = ov25FTHome;
    }

    public void setUn05HTAway(int un05HTAway) {
        this.un05HTAway = un05HTAway;
    }

    public void setUn05HTHome(int un05HTHome) {
        this.un05HTHome = un05HTHome;
    }

    public void setUn15FTAway(int un15FTAway) {
        this.un15FTAway = un15FTAway;
    }

    public void setUn05HTHomeProb(double un05HTHomeProb) {
        this.un05HTHomeProb = un05HTHomeProb;
    }

    public void setGGFTHomeProb(double GGFTHomeProb) {
        this.GGFTHomeProb = GGFTHomeProb;
    }

    public void setUn15FTHome(int un15FTHome) {
        this.un15FTHome = un15FTHome;
    }

    public void setUn15HTAway(int un15HTAway) {
        this.un15HTAway = un15HTAway;
    }

    public void setUn15HTHome(int un15HTHome) {
        this.un15HTHome = un15HTHome;
    }

    public void setOv25FTHomeProb(double ov25FTHomeProb) {
        this.ov25FTHomeProb = ov25FTHomeProb;
    }

    public void setUn25FTAway(int un25FTAway) {
        this.un25FTAway = un25FTAway;
    }

    public void setUn15HTHomeProb(double un15HTHomeProb) {
        this.un15HTHomeProb = un15HTHomeProb;
    }

    public void setUn25FTHome(int un25FTHome) {
        this.un25FTHome = un25FTHome;
    }

    public void setXFTHome(int XFTHome) {
        this.XFTHome = XFTHome;
    }

    public void setXFTAway(int XFTAway) {
        this.XFTAway = XFTAway;
    }

    public void setOv05HTAwayProb(double ov05HTAwayProb) {
        this.ov05HTAwayProb = ov05HTAwayProb;
    }

    public void setGGFTAwayProb(double GGFTAwayProb) {
        this.GGFTAwayProb = GGFTAwayProb;
    }

    public void setOv15HTAwayProb(double ov15HTAwayProb) {
        this.ov15HTAwayProb = ov15HTAwayProb;
    }

    public void setOv25FTAwayProb(double ov25FTAwayProb) {
        this.ov25FTAwayProb = ov25FTAwayProb;
    }

    public void setUn05HTAwayProb(double un05HTAwayProb) {
        this.un05HTAwayProb = un05HTAwayProb;
    }

    public void setUn15FTAwayProb(double un15FTAwayProb) {
        this.un15FTAwayProb = un15FTAwayProb;
    }

    public void setUn15FTHomeProb(double un15FTHomeProb) {
        this.un15FTHomeProb = un15FTHomeProb;
    }

    public void setUn15HTAwayProb(double un15HTAwayProb) {
        this.un15HTAwayProb = un15HTAwayProb;
    }

    public void setUn25FTAwayProb(double un25FTAwayProb) {
        this.un25FTAwayProb = un25FTAwayProb;
    }

    public void setUn25FTHomeProb(double un25FTHomeProb) {
        this.un25FTHomeProb = un25FTHomeProb;
    }

    public void setXFTAwayProb(double XFTAwayProb) {
        this.XFTAwayProb = XFTAwayProb;
    }

    public void setXFTHomeProb(double XFTHomeProb) {
        this.XFTHomeProb = XFTHomeProb;
    }

    public double getGGHTHomeProb() {
        return GGHTHomeProb;
    }

    public int getGGHTAway() {
        return GGHTAway;
    }

    public int getGGHTHome() {
        return GGHTHome;
    }

    public double getGGHTAwayProb() {
        return GGHTAwayProb;
    }

    public void setGGHTAway(int GGHTAway) {
        this.GGHTAway = GGHTAway;
    }

    public void setGGHTAwayProb(double GGHTAwayProb) {
        this.GGHTAwayProb = GGHTAwayProb;
    }

    public void setGGHTHome(int GGHTHome) {
        this.GGHTHome = GGHTHome;
    }

    public void setGGHTHomeProb(double GGHTHomeProb) {
        this.GGHTHomeProb = GGHTHomeProb;
    }

    public int getNGFTHome() {
        return NGFTHome;
    }

    public double getNGFTAwayProb() {
        return NGFTAwayProb;
    }

    public double getNGFTHomeProb() {
        return NGFTHomeProb;
    }

    public int getNGFTAway() {
        return NGFTAway;
    }

    public void setNGFTHome(int NGFTHome) {
        this.NGFTHome = NGFTHome;
    }

    public void setNGFTAway(int NGFTAway) {
        this.NGFTAway = NGFTAway;
    }

    public void setNGFTAwayProb(double NGFTAwayProb) {
        this.NGFTAwayProb = NGFTAwayProb;
    }

    public void setNGFTHomeProb(double NGFTHomeProb) {
        this.NGFTHomeProb = NGFTHomeProb;
    }
}
