package app.gangdan.please.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GooglePlacesResponse {

    @JsonProperty("candidates")
    private List<Candidate> candidates;

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public static class Candidate {
        @JsonProperty("name")
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
