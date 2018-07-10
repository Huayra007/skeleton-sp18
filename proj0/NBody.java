public class NBody {
    public static double readRadius(String path){
        In in = new In(path);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String path){
        In in = new In(path);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet P[] = new Planet[num];
        for (int i = 0; i < num; i++)
            P[i] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
        return P;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] P = readPlanets(filename);
        int num = P.length;
        P[0].draw();
        StdDraw.enableDoubleBuffering();
        StdDraw.show();
        for (double t = 0; t < T; t += dt){
            double xForce[] = new double[num];
            double yForce[] = new double[num];
            xForce[0] = P[0].calcNetForceExertedByX(new Planet[]{P[1],P[2],P[3],P[4]});
            xForce[1] = P[1].calcNetForceExertedByX(new Planet[]{P[0],P[2],P[3],P[4]});
            xForce[2] = P[2].calcNetForceExertedByX(new Planet[]{P[1],P[0],P[3],P[4]});
            xForce[3] = P[3].calcNetForceExertedByX(new Planet[]{P[1],P[2],P[0],P[4]});
            xForce[4] = P[4].calcNetForceExertedByX(new Planet[]{P[1],P[2],P[3],P[0]});
            for (int i = 0; i < num; i++)
                P[i].update(dt,xForce[i],yForce[i]);
            for (Planet p : P)
                p.draw();
            StdDraw.show();
            StdDraw.pause(10);
        }
    }
}
