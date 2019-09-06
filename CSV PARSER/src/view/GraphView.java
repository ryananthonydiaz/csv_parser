package view;


import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import model.*;


/**
 * Class GraphView which extends the parameterized LineChart from javafx.scene package. GraphView is used to create
 * a Data Series from each country object.
 */
public class GraphView extends LineChart<Number, Number> {

    /**
     * Instance variable of type NumberAxis which represents the X-Axis of the LineChart
     */
    private NumberAxis xAxis;

    /**
     * Instance variable of type NumberAxis which represents the Y-Axis of the LineChart
     */
    private NumberAxis yAxis;

    /**
     * Instance variable "model" of type DataModel which provides access to the data read from the CSV file(s)
     */
    private DataModel model;


    /**
     * A constructor which initializes the axis and data model of the current instance. Receives one argument: a
     * DataModel object which is set to the model instance variable. The constructor also initializes the XAxis and YAxis
     * by calling the super() class.
     *
     * @param model
     */
    GraphView(DataModel model) {
        super(new NumberAxis(model.getModel()[0].getStartYear(), model.getModel()[0].getEndYear(), 5), new NumberAxis(0, 115, 5));
        this.model = model;
        this.xAxis = (NumberAxis) super.getXAxis();
        this.yAxis = (NumberAxis) super.getYAxis();
        this.xAxis.setLabel(model.getXAxisName());
        this.yAxis.setLabel(model.getYAxisName());
        if (this.model.getIndicatorType() == IndicatorType.GDP_PER_CAPITA) {
            this.yAxis.setUpperBound(50_000);
            this.yAxis.setTickUnit(1_000);
        }
    }

    /**
     * The method seriesFromCountry() which creates a Series object will all the years and corresponding data of the
     * current Country Object
     *
     * @param country
     * @return
     */
    private Series<Number, Number> seriesFromCountry(Country country) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(country.getName());
        for (Indicator ind : country.getIndicators()) {
            series.getData().add(new XYChart.Data<>(ind.getYear(), ind.getData()[0]));
        }
        return series;
    }

    /**
     * The method .update() which goes through the list of Country objects and creates a series from each element
     */
    public void update() {
        int randOne = (int) (Math.random() * this.model.getModel().length) + 1;
        int randTwo = (int) (Math.random() * this.model.getModel().length) + 1;
        int randThree = (int) (Math.random() * this.model.getModel().length) + 1;
        int randFour = (int) (Math.random() * this.model.getModel().length) + 1;
        int randFive = (int) (Math.random() * this.model.getModel().length) + 1;
        int randSix = (int) (Math.random() * this.model.getModel().length) + 1;
        for (int i = 0; i < this.model.getModel().length; i++) {
            if (i == randOne || i == randTwo || i == randThree || i == randFour || i == randFive || i == randSix) {
                XYChart.Series<Number, Number> someSeries = this.seriesFromCountry(this.model.getModel()[i]);
                this.getData().add(someSeries);
            }
        }
    }


}
