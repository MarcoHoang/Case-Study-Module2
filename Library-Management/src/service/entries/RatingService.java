package service.entries;

import config.AppConfig;
import model.Rating;
import service.interfaces.IRatingService;
import storage.GenericFileStorage;

import java.util.ArrayList;
import java.util.List;

public class RatingService implements IRatingService {
    private static RatingService instance;
    private final GenericFileStorage<Rating> storage;
    private List<Rating> ratings;

    private RatingService() {
        this.storage = new GenericFileStorage<>();
        this.ratings = storage.readFromFile(AppConfig.RATING_FILE);
        if (this.ratings == null) {
            this.ratings = new ArrayList<>();
        }
    }

    public static RatingService getInstance() {
        if (instance == null) {
            synchronized (RatingService.class) {
                if (instance == null) {
                    instance = new RatingService();
                }
            }
        }
        return instance;
    }

    @Override
    public void addRating(Rating rating) {
        ratings.add(rating);
        storage.writeToFile(AppConfig.RATING_FILE, ratings);
    }

    @Override
    public List<Rating> getAllRatings() {
        return new ArrayList<>(ratings);
    }
}