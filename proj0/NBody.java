public class NBody {
    public static double readRadius(String filename) {
        In in = new In(filename);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int N = in.readInt();
        in.readDouble();
        Planet[] planets = new Planet[N];
        for (int i = 0; i < N; i++) {
            planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
        }
        return planets;
    }

    public static void drawBackground(double radius) {
        StdDraw.setScale(-radius, radius);
        /* Clears the drawing window. */
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
    }

    public static void drawPlanets(Planet[] planets) {
        for (Planet planet : planets) {
            planet.draw();
        }
    }


    public static void main(String[] args) {
        // This is a graphics technique to prevent flickering in the animation.
        StdDraw.enableDoubleBuffering();

        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        drawBackground(radius);
        drawPlanets(planets);

        double t = 0;
        while (t < T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i = 0; i < planets.length; i++) {
                Planet planet = planets[i];
                xForces[i] = planet.calcNetForceExertedByX(planets);
                yForces[i] = planet.calcNetForceExertedByX(planets);
            }

            /*
            For each time through the main loop, do not make any calls to update until all forces have been calculated
            and safely stored in xForces and yForces.  For example, don’t call planets[0].update() until after the entire
            xForces and yForces arrays are done! The difference is subtle, but the autograder will be upset if you call
            planets[0].update before you calculate xForces[1] and yForces[1].
             */
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            drawBackground(radius);
            drawPlanets(planets);
            StdDraw.show();
            StdDraw.pause(1);
            t += dt;
        }

        // Printing the Universe
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
