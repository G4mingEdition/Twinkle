package be.twinkle.utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

@SuppressWarnings({ "serial", "rawtypes" })
public class FilterComboBox extends JComboBox {
    private List<String> array;

    @SuppressWarnings("unchecked")
	public FilterComboBox(List<String> array) {
        super(array.toArray());
        this.array = array;
        this.setEditable(true);
        final JTextField textfield = (JTextField) this.getEditor().getEditorComponent();
        textfield.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                comboFilter(textfield.getText());
            }
        });

    }

    @SuppressWarnings("unchecked")
	public void comboFilter(String enteredText) {
        List<String> filterArray= new ArrayList<String>();
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).toLowerCase().contains(enteredText.toLowerCase())) {
                filterArray.add(array.get(i));
            }
        }
        if (filterArray.size() > 0) {
            this.setModel(new DefaultComboBoxModel(filterArray.toArray()));
            this.setSelectedItem(enteredText);
            this.showPopup();
        }
        else {
            this.hidePopup();
        }
    }

	public List<String> getArray() {
		return array;
	}

	public void setArray(List<String> array) {
		this.array = array;
	}

}