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

    public double calcNetForceExertedByX(Planet[] planets) {
        double sum = 0;
        for (Planet planet : planets) {
            /* don't calc the forces exerted by itself */
            if (this.equals(planet)) {
                continue;
            }
            sum += calcForceExertedByX(planet);
        }
        return sum;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double sum = 0;
        for (Planet planet : planets) {
            /* don't calc the forces exerted by itself */
            if (this.equals(planet)) {
                continue;
            }
            sum += calcForceExertedByY(planet);
        }
        return sum;
    }

    /**
     * A method that determines how much the forces exerted on the planet will cause that planet to accelerate, and the
     * resulting change in the planet’s velocity and position in a small period of time dt. For example,
     * samh.update(0.005, 10, 3) would adjust the velocity and position if an x-force of 10 Newtons and a y-force of 3
     * Newtons were applied for 0.005 seconds.
     * */
    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel += aX * dt;
        yyVel += aY * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
