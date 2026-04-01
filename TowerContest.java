import javax.swing.JOptionPane;

/**
 * Resuelve y simula el problema Stacking Cups.
 * Tower se usa ÚNICAMENTE para simular, no para resolver.
 */
public class TowerContest{
    /**
     * Resuelve el problema dado n tazas y altura h deseada.
     * @param n número de tazas
     * @param h altura deseada
     * @return alturas en orden o "impossible"
     */
    public static String solve(int n, int h){
        if (n <= 0 || h <= 0) return "impossible";

        long alturaMax = (long) n * n;
        long alturaMin = (long) (2 * n - 1);

        if (h > alturaMax || h < alturaMin) return "impossible";

        int k = encontrarK(n, h);

        if (k < 1) return "impossible";

        return construirSalida(n, k);
    }

    /**
     * Simula visualmente la solución usando Tower.
     * @param n número de tazas
     * @param h altura deseada
     */
    public static void simulate(int n, int h){
        String resultado = solve(n, h);

        if ("impossible".equals(resultado)) {
            JOptionPane.showMessageDialog(null,
                "No es posible construir la torre.\n" +
                "n=" + n + "  h=" + h + "\n" +
                "Mínimo: " + (2*n-1) + " cm  Máximo: " + ((long)n*n) + " cm",
                "Imposible", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int k    = encontrarK(n, h);
        Tower torre = new Tower(2 * n - 1, n * n);

        for (int rep = n - k + 1; rep <= n; rep++) {
            torre.pushCup(rep);
        }

        torre.makeVisible();

        JOptionPane.showMessageDialog(null,
            "n=" + n + "  h=" + h + "\n\n" +
            "Orden: " + resultado + "\n" +
            "Grupos: " + k + "  Operaciones: " + (n - k),
            "Simulación", JOptionPane.INFORMATION_MESSAGE);
    }

    //Helpers privados para solve y para simulate que funcionen correctamente
    /**
     * Encuentra k máximo tal que k*(2n-k) <= h.
     */
    private static int encontrarK(int n, long h){
        for (int k = n; k >= 1; k--) {
            if ((long) k * (2 * n - k) <= h) return k;
        }
        return 0;
    }

    /**
     * Construye la salida con las alturas en el orden correcto.
     */
    private static String construirSalida(int n, int k){
        StringBuilder sb   = new StringBuilder();
        int rep1 = n - k + 1;

        sb.append(2 * rep1 - 1);

        for (int i = rep1 - 1; i >= 1; i--) {
            sb.append(" ").append(2 * i - 1);
        }

        for (int rep = rep1 + 1; rep <= n; rep++) {
            sb.append(" ").append(2 * rep - 1);
        }

        return sb.toString();
    }
}