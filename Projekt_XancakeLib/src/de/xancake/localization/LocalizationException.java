package de.xancake.localization;

import java.text.MessageFormat;

public class LocalizationException extends RuntimeException {
	private static final long serialVersionUID = -5822513758051420237L;
	private static final String MESSAGE = "Couldn't find translation for key '{0}'";

	public LocalizationException(String key) {
		super(MessageFormat.format(MESSAGE, key));
	}
	
	public LocalizationException(String key, Throwable cause) {
		super(MessageFormat.format(MESSAGE, key), cause);
	}
}
