package com.test.WriteInTxt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.RecordComponent;
import java.util.List;

import com.test.Collections.CombinedTransactionRecord;
import com.test.annotation.FixedWidthFormatter;
import com.test.annotation.FieldLength;

public class WriteInTxt {

	public static void writeToFile(List<CombinedTransactionRecord> data, String fileName) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			if (data.isEmpty()) return;


			String header = buildHeader(CombinedTransactionRecord.class);
			writer.write(header);
			writer.newLine();


			for (CombinedTransactionRecord record : data) {
				writer.write(FixedWidthFormatter.formatRecord(record));
				writer.newLine();
			}
		}
	}

	private static String buildHeader(Class<?> clazz) {
		StringBuilder sb = new StringBuilder();
		buildHeaderRecursive(clazz, sb, " ");
		return sb.toString();
	}

	private static void buildHeaderRecursive(Class<?> clazz, StringBuilder sb, String prefix) {
		if (!clazz.isRecord()) return;

		for (RecordComponent component : clazz.getRecordComponents()) {
			Class<?> type = component.getType();
			FieldLength annotation = component.getAnnotation(FieldLength.class);

			if (annotation != null) {
				String fieldName = component.getName();
				sb.append(padRight(fieldName, annotation.length()));
			} else if (type.isRecord()) {
				buildHeaderRecursive(type, sb,  component.getName() + " ");
			}
		}
	}

	private static String padRight(String str, int size) {
		return str.length() >= size ? str.substring(0, size) : String.format("%-" + size + "s", str);

	}
}