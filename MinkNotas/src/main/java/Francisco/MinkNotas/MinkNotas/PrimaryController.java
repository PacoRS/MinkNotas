package Francisco.MinkNotas.MinkNotas;

import javafx.scene.control.Button;
import java.io.IOException;
import java.util.List;
import javafx.scene.control.TextField;
import Francisco.MinkNotas.MinkNotas.Model.Dao.User;
import Francisco.MinkNotas.MinkNotas.Model.Dao.UserDao;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleObjectProperty;
import java.util.Observable;
import Francisco.MinkNotas.MinkNotas.Model.Dao.Notas;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;

public class PrimaryController {
	@FXML
	private Label nombreLAbel;
	@FXML
	private Label edadLAbel;
	@FXML
	private Label dniLAbel;
	@FXML
	private Label idLAbel;

	// Declaramos los botones
	@FXML
	private Button aniadirBT;
	@FXML
	private Button modificarBT;
	@FXML
	private Button eliminarBT;
	@FXML
	private Button nuevoBT;
	@FXML
	private Button vernotasBT;
	@FXML
	private Button refreshBT;

	// Declaramos los textfileds
	@FXML
	private TextField nombreTF;
	@FXML
	private TextField edadTF;
	@FXML
	private TextField dniTF;
	@FXML
	private TextField idTF;

	@FXML
	private TableView<User> tablauser;
	@FXML
	private TableColumn<User, String> nombreColumna;
	@FXML
	private TableColumn<User, String> edadColumna;
	@FXML
	private TableColumn<User, String> dniColumna;
	@FXML
	private TableColumn<User, String> idColumna;

	@FXML
	protected void initialize() {
		System.out.println("Cargando...");
		// muestrainfo(null);
		configuraTabla();
		List<User> todos = UserDao.listarTodos();
		tablauser.setItems(FXCollections.observableArrayList(todos));
		tablauser.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			muestrainfo(newValue);
		});

		// muestrainfo(newValue);
		// });

	}

	private void configuraTabla() {
		dniColumna.setCellValueFactory(cadapersona -> {
			SimpleStringProperty v = new SimpleStringProperty();
			v.setValue(cadapersona.getValue().getDni());
			return v;
		});
		nombreColumna.setCellValueFactory(cadapersona -> {
			SimpleStringProperty v = new SimpleStringProperty();
			v.setValue(cadapersona.getValue().getNombre());
			return v;
		});
		edadColumna.setCellValueFactory(cadapersona -> {
			SimpleObjectProperty v = new SimpleObjectProperty();
			v.setValue(cadapersona.getValue().getEdad());
			return v;
		});
		idColumna.setCellValueFactory(cadapersona -> {
			SimpleObjectProperty v = new SimpleObjectProperty();
			v.setValue(cadapersona.getValue().getId());
			return v;
		});

	}

	private void muestrainfo(User u) {
		if (u != null) {
			dniTF.setText(u.getDni());
			nombreTF.setText(u.getNombre());
			edadTF.setText(String.valueOf(u.getEdad()));
			idTF.setText(String.valueOf(u.getId()));
		} else {
			dniTF.setText("");
			nombreTF.setText("");
			edadTF.setText(String.valueOf(""));
			idTF.setText(String.valueOf(""));
		}
	}

	/*
	 * private void muestranotas(User u) { if (u != null) {
	 * dniTF.setText(u.getDni()); nombreTF.setText(u.getNombre());
	 * edadTF.setText(String.valueOf(u.getEdad())); } else { dniTF.setText("");
	 * nombreTF.setText(""); edadTF.setText(String.valueOf("")); } }
	 */

	public void aniadir(ActionEvent event) {
		UserDao u = new UserDao();
		u.setNombre(nombreTF.getText());
		u.setEdad(Integer.parseInt(edadTF.getText()));
		u.setDni(nombreTF.getText());
		u.guardarUser();
	}

	public void eliminar(ActionEvent event) {
		UserDao u = new UserDao();
		int a = tablauser.getSelectionModel().getSelectedItem().getId();
		u.setId(a);
		u.eliminarUser();

	}
	public void buscarUser(User u) {
		UserDao q=new UserDao();
		int a = tablauser.getSelectionModel().getSelectedItem().getId();
		q.buscaPorID(a);
		

	}
	public void ModificarUser(ActionEvent event) {
		UserDao u = new UserDao();
		u.setNombre(nombreTF.getText());
		u.setEdad(Integer.parseInt(edadTF.getText()));
		u.setDni(dniTF.getText());
		u.setId(Integer.parseInt(idTF.getText()));
		u.actualizarUsaer();

	}

	 @FXML
	    private void switchToSecondary() throws IOException {
	        App.setRoot("secondary");
	    }
	 @FXML
		private void switchToPrimary() throws IOException {
			App.setRoot("primary");
		}

}
