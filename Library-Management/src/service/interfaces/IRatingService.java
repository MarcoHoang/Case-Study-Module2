package service.interfaces;

import model.Rating;

import java.util.List;

public interface IRatingService {
    void addRating(Rating rating);
    List<Rating> getAllRatings();
}
