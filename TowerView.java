import java.util.Stack;
import java.util.ArrayList;

/**
 * Se encarga de dibujar la torre.
 */
public class TowerView
{
    private ArrayList<Rectangle> shapes;

    /**
     * Crea la vista de la torre.
     */
    public TowerView()
    {
        shapes = new ArrayList<Rectangle>();
    }

    /**
     * Muestra la torre en el canvas.
     * @param items elementos de la torre
     * @param width ancho de referencia
     * @param maxHeight altura máxima
     */
    public void show(Stack<StackItem> items, int width, int maxHeight)
    {
        hide();

        int baseX = 120;
        int baseY = 300;
        int scale = 12;
        int currentY = baseY;

        for (int i = 0; i < items.size(); i++) {
            StackItem item = items.get(i);

            int itemHeight = item.getHeight() * scale;
            int itemWidth = item.getWidth() * scale;

            Rectangle block = new Rectangle();
            block.changeSize(itemHeight, itemWidth);
            block.moveHorizontal(baseX - 70 + ((width * scale) - itemWidth) / 2);
            block.moveVertical(currentY - itemHeight);
            block.changeColor(item.getColor());
            block.makeVisible();

            shapes.add(block);
            currentY = currentY - itemHeight;
        }
    }

    /**
     * Oculta la torre del canvas.
     */
    public void hide()
    {
        for (int i = 0; i < shapes.size(); i++) {
            shapes.get(i).makeInvisible();
        }
        shapes.clear();
    }
}