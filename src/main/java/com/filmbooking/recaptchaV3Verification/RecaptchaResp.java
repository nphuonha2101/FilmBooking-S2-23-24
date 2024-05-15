package com.filmbooking.recaptchaV3Verification;

import lombok.Getter;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */

@Getter
public class RecaptchaResp {
    private boolean success;
    private String challenge_ts;
    private String hostname;
    private double score;
    private String action;

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
