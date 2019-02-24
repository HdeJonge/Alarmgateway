package nl.bprocare.alarmgateway.config;

import java.util.Locale;

import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;

import nl.bprocare.alarmgateway.dto.LabelDTO;

public class LabelFormatter implements Formatter<LabelDTO> {

	@Override
	public String print(LabelDTO label, Locale locale) {
		return label.getId().toString() + "-" + label.getDescription();
	}

	@Override
	public LabelDTO parse(String text, Locale locale) throws ParseException {
		String[] split = text.split("-");
		LabelDTO label = new LabelDTO();
		label.setId(Long.parseLong(split[0]));
		label.setDescription(split[1]);
		return label;
	}
}