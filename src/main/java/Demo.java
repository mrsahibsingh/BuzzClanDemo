import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class Demo {

	public static void main(String[] args) {
		String resultFile = "src/main/resources/Demo.json";
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter file path: ");
		String filePath = sc.nextLine();
		System.out.println("Path you have entered: " + filePath);
		sc.close();
		File file = new File(filePath);
		if (file.exists()) {
			try {
				sc = new Scanner(new File(filePath));
				sc.useDelimiter(",");
				CsvSchema schema = CsvSchema.emptySchema().withHeader();
				CsvMapper csvMapper = new CsvMapper();
				MappingIterator<DemoLine> orderLines = csvMapper.readerFor(DemoLine.class).with(schema)
						.readValues(new File(filePath));
				new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true).writeValue(new File(resultFile),
						orderLines.readAll());

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Does not Exists");
		}

	}

}
