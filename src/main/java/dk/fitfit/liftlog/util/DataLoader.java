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
/*
		List<MetaData> data = Lists.newArrayList(
				new MetaData(Exercise.class, "data/exercise.csv", exerciseRepository)
				new MetaData(User.class, "data/users.csv", userRepository),
				new MetaData(Session.class, "data/session.csv", sessionRepository),
				new MetaData(WorkoutSet.class, "data/workout_set.csv", workoutSetRepository)
		);

		for (MetaData metaData : data) {
			loadData(metaData.domainClass, metaData.dataFile, metaData.repository);
		}
*/
	}
/*
	private void loadData(Class<? extends DomainObject> domainClass, String dataFile, CrudRepository<? extends DomainObject, Long> repository) {
		List<? extends DomainObject> domainObjects = loadObjectList(domainClass, dataFile);
		for (DomainObject domainObject : domainObjects) {
			if (domainObject instanceof Exercise)
				repository.save((Exercise) domainObject);
//				if (domainObject instanceof Session)
//					repository.save((Session) domainObject);
		}
	}
*/

	private void loadExercises(ExerciseRepository exerciseRepository) {
		List<Exercise> exercises = loadObjectList(Exercise.class, "data/exercise.csv");
		try {
			exercises.forEach(exerciseRepository::save);
		} catch (DataIntegrityViolationException e) {
			String message = e.getMostSpecificCause().getMessage();
			System.err.println(message);
		}
	}
/*
	private void loadUsers(UserRepository userRepository) {
		List<User> users = loadObjectList(User.class, "data/users.csv");
		users.forEach(userRepository::save);
	}

	private void loadSessions(SessionRepository sessionRepository) {
		List<Session> sessions = loadObjectList(Session.class, "data/session.csv");
		sessions.forEach(sessionRepository::save);
	}

	private void loadWorkoutSets(WorkoutSetRepository workoutSetRepository) {
		List<WorkoutSet> workoutSets = loadObjectList(WorkoutSet.class, "data/workout_set.csv");
		workoutSets.forEach(workoutSetRepository::save);
	}
*/
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
/*
	private class MetaData {
		Class<? extends DomainObject> domainClass;
		String dataFile;
		CrudRepository<? extends DomainObject, Long> repository;

		MetaData(Class<? extends DomainObject> domainClass, String dataFile, CrudRepository<? extends DomainObject, Long> repository) {
			this.domainClass = domainClass;
			this.dataFile = dataFile;
			this.repository = repository;
		}
	}
*/
}
