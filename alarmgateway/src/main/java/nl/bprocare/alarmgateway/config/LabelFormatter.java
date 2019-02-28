package nl.bprocare.alarmgateway.config;

import java.util.Locale;

import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;

import nl.bprocare.alarmgateway.dto.EditLabelDTO;

public class LabelFormatter implements Formatter<EditLabelDTO> {

	@Override
	public String print(EditLabelDTO label, Locale locale) {
		return label.getId().toString() + "-" + label.getDescription();
	}

	@Override
	public EditLabelDTO parse(String text, Locale locale) throws ParseException {
		String[] split = text.split("-");
		EditLabelDTO label = new EditLabelDTO();
		label.setId(Long.parseLong(split[0]));
		label.setDescription(split[1]);
		return label;
	}
}