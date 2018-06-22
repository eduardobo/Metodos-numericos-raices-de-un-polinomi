import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
public class CGrafica {
    XYSeriesCollection datos=new XYSeriesCollection();//en las series xy son para agregar pares ordenados
    JFreeChart grafica;//
    String titulo,tx="x",ty="f(x)";
    
    public CGrafica(String titulo)
    {
        this.titulo=titulo;
        grafica=ChartFactory.createXYLineChart(titulo, tx, ty, datos, PlotOrientation.VERTICAL, true, true, true);
    }
    
    public void crearGrafica(String id,double[] x, double[]y)
    {
        datos.removeAllSeries();
        agregarGrafica(id, x, y);
    }
    
    public void agregarGrafica(String id,double[] x,double[] y)
    {
        XYSeries s=new XYSeries(id);
        int n=x.length;
        for(int i=0;i<n;i++)
        {
            s.add(x[i], y[i]);
        }
        datos.addSeries(s);
    }
    public JPanel obtienePanel()
    {
        return new ChartPanel(grafica);
    }

    void agregarGrafica(String polinomio, float[] x, float[] y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}