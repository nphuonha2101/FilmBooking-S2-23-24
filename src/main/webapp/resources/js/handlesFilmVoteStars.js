import {$$} from "./utils.js";

let handlesFilmVoteStars = {
  start: () => {
    try {
      let voteStars = Array.from($$(".film-vote-stars"));

      for (const voteStar of voteStars) {
        voteStar.addEventListener("mouseover", () => {
          let currentId = voteStar.id;
          for (const voteStar of voteStars) {
            if (voteStar.id <= currentId) {
              voteStar.style.backgroundColor = "#ffe0a3";
              voteStar.style.borderRadius = "100px";
            }
          }
        });


        voteStar.addEventListener("mouseout", () => {
          for (const voteStar of voteStars) {
            voteStar.style.backgroundColor = ""; // Reset to original color
          }
        });
      }
    } catch (e) {
      console.error(e);
    }
  }
}

handlesFilmVoteStars.start();

