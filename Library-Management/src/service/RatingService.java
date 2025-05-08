package service;

import model.Rating;
import storage.RatingStorage;
import java.util.List;

public class RatingService implements IRatingService {
    private final RatingStorage storage = new RatingStorage();

    @Override
    public void addRating(Rating rating) {
        List<Rating> ratings = storage.readFromFile("ratings.dat");
        ratings.add(rating);
        storage.writeToFile("ratings.dat", ratings);
    }

    @Override
    public List<Rating> getAllRatings() {
        return storage.readFromFile("ratings.dat");
    }
}
