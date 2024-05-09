package com.filmbooking.recaptchaV3Verification;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public class RecaptchaResp {
    private boolean success;
    private String challenge_ts;
    private String hostname;
    private double score;
    private String action;

    public boolean isSuccess() {
        return success;
    }

    public double getScore() {
        return score;
    }

    public String getAction() {
        return action;
    }

    public String getHostname() {
        return hostname;
    }

    public String getChallenge_ts() {
        return challenge_ts;
    }

    @Override
    public String toString() {
        return "RecaptchaResp{" +
                "success=" + success +
                ", challenge_ts='" + challenge_ts + '\'' +
                ", score=" + score +
                ", action='" + action + '\'' +
                ", hostname='" + hostname + '\'' +
                '}';
    }
}
