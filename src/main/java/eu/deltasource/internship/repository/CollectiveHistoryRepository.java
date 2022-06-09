package eu.deltasource.internship.repository;

import eu.deltasource.internship.exception.SetterValidationException;
import eu.deltasource.internship.model.history.CollectiveHistory;
import eu.deltasource.internship.model.user.User;
import eu.deltasource.internship.service.EBookService;
import eu.deltasource.internship.service.UserService;

import java.util.HashSet;
import java.util.Set;

/**
 * Repository for the Collective History
 */
public class CollectiveHistoryRepository {

    private static CollectiveHistoryRepository INSTANCE;

    private Set<CollectiveHistory> collectiveHistorySet;

    public CollectiveHistoryRepository() {
        collectiveHistorySet = new HashSet<>();
    }

    public static CollectiveHistoryRepository getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new CollectiveHistoryRepository();
        }
        return INSTANCE;
    }

    /**
     *
     * Adds a collective history of a certain user to the collective history set
     * @param history - parameter of type history to be added to the repository
     */
    public boolean addCollectiveHistory(CollectiveHistory history) {
        if (history == null) {
            throw new SetterValidationException("history");
        }

        return collectiveHistorySet.add(history);
    }

    /**
     * Finds a collective history by a user.
     *
     * @param user - user we use as a parameter for the search
     * @return CollectiveHistory of the given user or null if there is no collective history for the given user
     */
    public CollectiveHistory getCollectiveHistoryByUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Cannot find history if the user is invalid!");
        }

        CollectiveHistory history = null;

        for (CollectiveHistory collectiveHistory : collectiveHistorySet) {
            if (collectiveHistory.getUser().equals(user)) {
                history = collectiveHistory;
                return history;
            }
        }
        return history;
    }

    public Set<CollectiveHistory> getCollectiveHistorySet() {
        return collectiveHistorySet;
    }
}

