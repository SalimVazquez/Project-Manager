import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    @FXML // fx:id="command"
    private TextField command; // Value injected by FXMLLoader

    @FXML // fx:id="textAreaOutput"
    private TextArea textAreaOutput; // Value injected by FXMLLoader

    @FXML // fx:id="btnStart"
    private Button btnStart; // Value injected by FXMLLoader

    private boolean ctrl = false;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/index.fxml"));
        primaryStage.getIcons().add(new Image("/assets/logoUP.png"));
        primaryStage.setTitle("Manager Project");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
    }

    @FXML
    void btnStart() {
        if (command.getText().equals(null)) {
            textAreaOutput.setText("Es necesario ingresar algun comando");
        } else {
            if (ctrl == false) {
                // Star command
                textAreaOutput.clear();;
                ctrl = true;
                Pattern pattern;
                Matcher matcher;
                btnStart.setDisable(true);
                int index = 0;
                String[] commands_split = command.getText().split("\\s");
                pattern = Pattern.compile("generate");
                matcher = pattern.matcher(commands_split[index]);
                if (matcher.find()) {
                    try {
                        index++;
                        pattern = Pattern.compile("(^-h$|^-v$|^about$|^browse$)");
                        matcher = pattern.matcher(commands_split[index]);
                        if (matcher.find()) {
                            if (commands_split.length - 1 > index) {
                                textAreaOutput.setText("1 > Ya no se esperaban mas argumentos, despues de '"
                                        + commands_split[index] + "'\n");
                            } else {
                                if (commands_split[index].equals("-h"))
                                    writeGenerate();
                                else if (commands_split[index].equals("-v"))
                                    writeVersion();
                                else if (commands_split[index].equals("about"))
                                    writeAbout();
                                else if (commands_split[index].equals("browse"))
                                    openBrowser();
                            }
                        } else {
                            pattern = Pattern.compile("(^create\\-project$|^show$)");
                            matcher = pattern.matcher(commands_split[index]);
                            if (matcher.find()) {
                                if (commands_split[index].equals("create-project")) {
                                    index++;
                                    pattern = Pattern.compile("(^HTML$|^Node$|^Laravel$|^Angular$)");
                                    matcher = pattern.matcher(commands_split[index]);
                                    if (matcher.find()) {
                                        if (commands_split[index].equals("HTML")) {
                                            index++;
                                            pattern = Pattern.compile("[a-zA-Z]+");
                                            matcher = pattern.matcher(commands_split[index]);
                                            if (matcher.find()) {
                                                if (commands_split.length - 1 > index) {
                                                    textAreaOutput.setText(
                                                            "1 > Ya no se esperaban mas argumentos, despues de '"
                                                                    + commands_split[index] + "'\n");
                                                } else
                                                    cloneProjectHTML(commands_split[index]);
                                            } else
                                                textAreaOutput.setText(
                                                        "1 > El nombre del proyecto HTML solo puede ser letras minusculas y/o mayusculas\n");
                                        } else if (commands_split[index].equals("Node")) {
                                            index++;
                                            pattern = Pattern.compile("[a-zA-Z]+");
                                            matcher = pattern.matcher(commands_split[index]);
                                            if (matcher.find())
                                                if (commands_split.length - 1 > index) {
                                                    textAreaOutput.setText(
                                                            "1 > Ya no se esperaban mas argumentos, despues de '"
                                                                    + commands_split[index] + "'\n");
                                                } else
                                                    cloneProjectNode(commands_split[index]);
                                            else
                                                textAreaOutput.setText(
                                                        "1 > El nombre del proyecto Node solo puede ser letras minusculas y/o mayusculas\n");
                                        } else if (commands_split[index].equals("Laravel")) {
                                            index++;
                                            pattern = Pattern.compile("[a-zA-Z]+");
                                            matcher = pattern.matcher(commands_split[index]);
                                            if (matcher.find())
                                                if (commands_split.length - 1 > index) {
                                                    textAreaOutput.setText(
                                                            "1 > Ya no se esperaban mas argumentos, despues de '"
                                                                    + commands_split[index] + "'\n");
                                                } else
                                                    cloneProjectLaravel(commands_split[index]);
                                            else
                                                textAreaOutput.setText(
                                                        "1 > El nombre del proyecto Laravel solo puede ser letras minusculas y/o mayusculas\n");
                                        } else if (commands_split[index].equals("Angular")) {
                                            index++;
                                            pattern = Pattern.compile("[a-zA-Z]+");
                                            matcher = pattern.matcher(commands_split[index]);
                                            if (matcher.find())
                                                if (commands_split.length - 1 > index) {
                                                    textAreaOutput.setText(
                                                            "1 > Ya no se esperaban mas argumentos, despues de '"
                                                                    + commands_split[index] + "'\n");
                                                } else
                                                    cloneProjectAngular(commands_split[index]);
                                            else
                                                textAreaOutput.setText(
                                                        "1 > El nombre del proyecto Angular solo puede ser letras minusculas y/o mayusculas\n");
                                        }
                                    } else
                                        textAreaOutput.setText(
                                                "1 > '" + commands_split[index] + "' no se reconoce como un tipo\n");
                                } else if (commands_split[index].equals("show")) {
                                    index++;
                                    pattern = Pattern.compile("(^HTML$|^Node$|^Laravel$|^Angular$)");
                                    matcher = pattern.matcher(commands_split[index]);
                                    if (matcher.find()) {
                                        if (commands_split[index].equals("HTML"))
                                            showProjectHTML();
                                        else if (commands_split[index].equals("Node"))
                                            showProjectNode();
                                        else if (commands_split[index].equals("Laravel"))
                                            showProjectLaravel();
                                        else if (commands_split[index].equals("Angular"))
                                            showProjectAngular();
                                    } else
                                        textAreaOutput.setText(
                                                "1 > '" + commands_split[index] + "' no se reconoce como un tipo\n");
                                }
                            } else
                                textAreaOutput.setText("1 > '" + commands_split[index]
                                        + "' no se reconoce como comando interno o externo\n");
                        }
                    } catch (ArrayIndexOutOfBoundsException exception) {
                        textAreaOutput.setText("Se esperaban mas argumentos\n");
                        writeGenerate();
                    }
                } else
                    textAreaOutput.setText(
                            "1 > '" + commands_split[index] + "' no se reconoce como comando interno o externo\n");
                // finished command
                ctrl = false;
                btnStart.setDisable(false);
            } else {
                Alert alert = new Alert(AlertType.ERROR, "Se esta ejecutando un comando", ButtonType.OK);
                alert.showAndWait();
            }
        }
    }

    void writeGenerate() {
        textAreaOutput.appendText(
                "1 > Generate v1.0, 20/07/2020 10:06\n2 > Uso:\n3 > generate [options]\n4 > Options:\n5 >     -h      Muestra ayuda de los comandos\n6 >     -v      Muestra la version de la app Generate\n7 >     about   Muestra una breve descripcion de la app Generate\n8 >     browse  Muestra los repositorios en el navegador\n9 >     create-project [arguments] Da la oportunidad de clonar un proyecto\n10 >    show [tipo]   Muestra la estructura de los proyectos disponibles\n11 > arguments disponibles:\n12 >    HTML [nombre_del_proyecto]\n13 >    Node [nombre_del_proyecto]\n14 >    Laravel [nombre_del_proyecto]\n15 >    Angular [nombre_del_proyecto]\n16 > tipo disponibles:\n17 >    HTML\n18 >    Node\n19 >    Laravel\n20 >    Angular\n");
    }

    void writeVersion() {
        textAreaOutput.appendText("1 > Generate v1.0, 20/07/2020 10:06:49");
    }

    void writeAbout() {
        textAreaOutput.appendText(
                "1 > Generate es una app, con la funcion de ahorrar el tiempo sobre la creacion y/o estructura de un proyecto.\n2 > Se encarga de clonar un proyecto determinado que haya sido publicado en un repositorio de Git\n3 > en un path determinado del computador.");
    }

    void openBrowser() {
        textAreaOutput.appendText("Abriendo el navegador predeterminado");
        String commandUrl = "START https://github.com/SalimVazquez";
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("cmd /c " + commandUrl);
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR, "Ocurrio un error al abrir el navegador", ButtonType.OK);
            alert.showAndWait();
        }
    }

    void showProjectHTML() {
        textAreaOutput.appendText("1 > Estructura del proyecto\n");
        textAreaOutput.appendText(
                "2 > HTML\n3 > ----->assets/\n4 > ---------->css/\n5 > ---------->images/\n6 > ---------->js/\n7 > ---------->sass/\n8 > ---------->webfonts/\n9 > ----->README.md\n10 > ----->index.html");
    }

    void showProjectNode() {
        textAreaOutput.appendText("1 > Estructura del proyecto\n");
        textAreaOutput.appendText(
                "2 > Node\n3 > ----->views/\n4 > ---------->assets/\n5 > ---------->index.html\n6 > ----->.gitignore\n7 > ----->README.md\n8 > ----->index.js\n9 > ----->package-lock.json\n10 > ----->package.json");
    }

    void showProjectLaravel() {
        textAreaOutput.appendText("1 > Estructura del proyecto\n");
        textAreaOutput.appendText(
                "2 > Laravel\n3 > ----->app/\n4 > ----->bootstrap/\n5 > ----->config/\n6 > ----->database/\n7 > ----->public/\n8 > ----->resources/\n9 > ----->routes/\n10 > ----->storage/\n11 > ----->tests/\n12 > ----->vendor/\n13 > ----->.editorconfig\n14 > ----->.env.example\n15 > ----->.gitattributes\n16 > ----->.gitignore\n17 > ----->.styleci\n18 > ----->artisan\n19 > ----->composer\n20 > ----->composer.lock\n21 > ----->package\n22 > ----->phpunit\n23 > ----->README\n24 > ----->server\n25 > ----->webpack.mix");
    }

    void showProjectAngular() {
        textAreaOutput.appendText("1 > Estructura del proyecto\n");
        textAreaOutput.appendText(
                "2 > Angular\n3 > ----->e2e/\n4 > ----->node_modules/\n5 > ----->src/\n6 > ----->.browserslistrc\n7 > ----->.editorconfig\n8 > ----->.gitignore\n9 > ----->angular\n10 > ----->karma.conf\n11 > ----->package\n12 > ----->package-lock\n13 > ----->README\n14 > ----->tsconfig.app\n15 > ----->tsconfig.base\n16 > ----->tsconfig\n17 > ----->tsconfig.spec\n18 > ----->tslint");
    }

    void cloneProjectHTML(String nameProject) {
        textAreaOutput.appendText("1 > Empezando a clonar proyecto de HTML...");
        String commandUrl = "git clone https://github.com/SalimVazquez/projectHTML.git";
        String directory = System.getProperty("user.dir");
        Runtime runtime = Runtime.getRuntime();
        try {
            // clone project
            runtime.exec("cmd /c " + commandUrl);
            File file = new File(directory+"/projectHTML/README.md");
            if (!file.exists()) {
                textAreaOutput.appendText("\n...");
            }
            textAreaOutput.appendText("\n2 > Proyecto de HTML ha sido clonado con exito!");
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR, "Ocurrio un error al clonar el proyecto", ButtonType.OK);
            alert.showAndWait();
        }
    }

    void cloneProjectNode(String nameProject) {
        textAreaOutput.appendText("1 > Empezando a clonar proyecto de Node...");
        String commandUrl = "git clone https://github.com/SalimVazquez/projectNode.git";
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("cmd /c " + commandUrl);
            textAreaOutput.appendText("\n2 > Proyecto de Node ha sido clonado con exito!");
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR, "Ocurrio un error al clonar el proyecto", ButtonType.OK);
            alert.showAndWait();
        }
    }

    void cloneProjectLaravel(String nameProject) {
        textAreaOutput.appendText("1 > Empezando a clonar proyecto de Laravel...");
        String commandUrl = "git clone https://github.com/SalimVazquez/projectLaravel.git";
        Runtime runtime = Runtime.getRuntime();
        try {
            // clone project
            runtime.exec("cmd /c " + commandUrl);
            textAreaOutput.appendText("\n2 > Proyecto de Laravel ha sido clonado con exito!");
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR, "Ocurrio un error al clonar el proyecto", ButtonType.OK);
            alert.showAndWait();
        }
    }

    void cloneProjectAngular(String nameProject) {
        textAreaOutput.appendText("1 > Empezando a clonar proyecto de Angular...");
        String commandUrl = "git clone https://github.com/SalimVazquez/projectAngular.git";
        Runtime runtime = Runtime.getRuntime();
        try {
            // clone project
            runtime.exec("cmd /c " + commandUrl);
            textAreaOutput.appendText("\n3 > Proyecto de Angular ha sido clonado con exito!");
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR, "Ocurrio un error al clonar el proyecto", ButtonType.OK);
            alert.showAndWait();
        }
    }
}