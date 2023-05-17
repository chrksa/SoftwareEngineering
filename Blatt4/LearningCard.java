import java.util.List;

// ------------------------------------------------------ //
public abstract class LearningCard {
    /** return the front site of the card */
    public abstract List<String> getFrontContent();

    /** return the back site of the card */
    public abstract List<String> getBackContent();

    /** return combined content of front and back */
    public abstract List<String> getContent();

    /**
     * write the content to the console in a meaningful way
     */
    public abstract void printToConsole();
}

// ------------------------------------------------------ //
class SimpleCard extends LearningCard {
}

// ------------------------------------------------------ //
class QuestionCard extends LearningCard {
}

// ------------------------------------------------------ //
// -------------- ToDo: add your own card --------------- //
// ------------------------------------------------------ //
