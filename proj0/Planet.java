public class Planet {
    private final static double G = 6.67e-11;

    public double xxPos;

    public double yyPos;

    public double xxVel;

    public double yyVel;

    public double mass;

    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet otherPlanet) {
        double dx = otherPlanet.xxPos - xxPos;
        double dy = otherPlanet.yyPos - yyPos;

        return Math.sqrt(dx * dx + dy * dy);/* pow() is slower */
    }

    public double calcForceExertedBy(Planet otherPlanet) {
        double distance = calcDistance(otherPlanet);
        return G * mass * otherPlanet.mass / (distance * distance);
    }

    /*
     * NOTE: Do not use Math.abs to fix sign issues with these methods. This will
     * cause issues later when drawing planets.
     */
    public double calcForceExertedByX(Planet otherPlanet) {
        double dx = otherPlanet.xxPos - xxPos;
        double distance = calcDistance(otherPlanet);
        return calcForceExertedBy(otherPlanet) * dx / distance;
    }

    public double calcForceExertedByY(Planet otherPlanet) {
        double dy = otherPlanet.yyPos - yyPos;
        double distance = calcDistance(otherPlanet);
        return calcForceExertedBy(otherPlanet) * dy / distance;
    }
}
