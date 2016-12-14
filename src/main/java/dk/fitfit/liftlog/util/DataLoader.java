package dk.fitfit.liftlog.util;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import dk.fitfit.liftlog.domain.Exercise;
import dk.fitfit.liftlog.repository.ExerciseRepository;
import dk.fitfit.liftlog.repository.SessionRepository;
import dk.fitfit.liftlog.repository.UserRepository;
import dk.fitfit.liftlog.repository.WorkoutSetRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Collections;
import java.util.List;

@Component
public class DataLoader {
	private static final Logger logger = Logger.getLogger(DataLoader.class);

	@Autowired
	public DataLoader(ExerciseRepository exerciseRepository, UserRepository userRepository, SessionRepository sessionRepository, WorkoutSetRepository workoutSetRepository) {
		loadExercises(exerciseRepository);
	}

	private void loadExercises(ExerciseRepository exerciseRepository) {
		List<Exercise> exercises = loadObjectList(Exercise.class, "data/exercise.csv");
		try {
			exercises.forEach(exerciseRepository::save);
		} catch (DataIntegrityViolationException e) {
			String message = e.getMostSpecificCause().getMessage();
			System.err.println(message);
		}
	}

	// Inspiration: http://www.baeldung.com/spring-app-setup-with-csv-files
	private <T> List<T> loadObjectList(Class<T> type, String fileName) {
		try {
			CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
			CsvMapper mapper = new CsvMapper();
			File file = new ClassPathResource(fileName).getFile();
			MappingIterator<T> readValues = mapper.readerFor(type).with(bootstrapSchema).readValues(file);
			return readValues.readAll();
		} catch (Exception e) {
			logger.error("Error occurred while loading object list from file " + fileName, e);
			return Collections.emptyList();
		}
	}
}
