package mate.academy.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import mate.academy.dao.MovieSessionDao;
import mate.academy.exception.DataProcessingException;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.MovieSession;
import mate.academy.service.MovieSessionService;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    @Inject
    private MovieSessionDao movieSessionDao;

    @Override
    public MovieSession add(MovieSession movieSession) {
        try {
            return movieSessionDao.add(movieSession);
        } catch (Exception e) {
            throw new DataProcessingException("Can't add movie session " + movieSession, e);
        }
    }

    @Override
    public MovieSession get(Long id) {
        Optional<MovieSession> optional = movieSessionDao.get(id);
        if (optional.isEmpty()) {
            throw new DataProcessingException("Movie session with id " + id + " not found", null);
        }
        return optional.get();
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDateTime dateTime) {
        try {
            return movieSessionDao.findAvailableSessions(movieId, dateTime);
        } catch (Exception e) {
            throw new DataProcessingException(
                    "Can't find available sessions for movie id "
                            + movieId + " after " + dateTime, e);
        }
    }
}

