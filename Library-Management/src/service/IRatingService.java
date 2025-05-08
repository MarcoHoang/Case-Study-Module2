package service;

import model.Rating;

import java.util.List;

public interface IRatingService {
    void addRating(Rating rating);
    List<Rating> getAllRatings();
}
