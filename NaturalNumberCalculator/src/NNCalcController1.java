import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 *
 * @author Nicholas Cheong
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     * @ensures [view has been updated to be consistent with model]
     */
    private static void updateViewToMatchModel(NNCalcModel model,
            NNCalcView view) {

        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();
        view.updateRootAllowed(model.bottom().compareTo(TWO) >= 0
                && model.bottom().compareTo(INT_LIMIT) <= 0);
        view.updateDivideAllowed(model.bottom().compareTo(INT_LIMIT) <= 0);
        view.updateSubtractAllowed(model.top().compareTo(model.bottom()) >= 0);
        view.updatePowerAllowed(model.bottom().compareTo(TWO) >= 0
                && model.bottom().compareTo(INT_LIMIT) <= 0);

        view.updateTopDisplay(top);
        view.updateBottomDisplay(bottom);

    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = top.newInstance();
        temp.transferFrom(top);
        top.transferFrom(bottom);
        bottom.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processEnterEvent() {

        // get aliases
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        // update model
        top.copyFrom(bottom);

        // update view
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddEvent() {

        // get aliases
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        // update model
        top.add(bottom);
        bottom.transferFrom(top);

        // update view
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processSubtractEvent() {

        // get aliases
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        // update model
        top.subtract(bottom);
        bottom.transferFrom(top);

        //update view
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processMultiplyEvent() {

        // get aliases
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        // update model
        top.multiply(bottom);
        bottom.transferFrom(top);

        // update view
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processDivideEvent() {

        // get aliases
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        // update model
        top.divide(bottom);
        bottom.transferFrom(top);

        // update view
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processPowerEvent() {

        // get aliases
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        int bottomInt = bottom.toInt();
        // update model
        top.power(bottomInt);
        bottom.transferFrom(top);

        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processRootEvent() {

        // get aliases
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        int bottomInt = bottom.toInt();

        // updated model
        top.root(bottomInt);
        bottom.transferFrom(top);

        // update view
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddNewDigitEvent(int digit) {

        // get aliases
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        // updated model
        bottom.multiplyBy10(digit);

        // update view
        updateViewToMatchModel(this.model, this.view);

    }

}
