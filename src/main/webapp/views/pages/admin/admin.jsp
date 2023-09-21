<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 16-09-2023
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="section">
    <div class="tab wrapper centered-content">
        <div class="tab-headers centered-content container">
            <label for="admin-film-content" class="tab-label active">Films Management</label>
            <label for="admin-registration-content" class="tab-label">Registrations Management</label>
        </div>

        <div class="tab-contents wrapper">
            <div id="admin-film-content" class="tab-content container active">
                <div class="two-col__wrapper header centered-content">
                    <h3>Film List</h3>
                    <h3>Add Film</h3>
                </div>
                <div class="two-col__wrapper content">
                    <div class="centered-content wrapper" id="film-list left">
                        <div class="slide wrapper">
                            <div class="film-card two-col__wrapper" id="card-1">
                                <div class="wrapper">
                                    <h2>Iron man</h2>
                                </div>
                                <div class="wrapper align-center">
                                    <button class="outlined-button rounded-button button">Delete</button>
                                    <button class="light-filled-button rounded-button button">Edit</button>
                                </div>
                            </div>

                            <div class="film-card two-col__wrapper" id="card-2">
                                <div class="wrapper">
                                    <h2>Iron man</h2>
                                </div>
                                <div class="wrapper align-center">
                                    <button class="outlined-button rounded-button button">Delete</button>
                                    <button class="light-filled-button rounded-button button">Edit</button>
                                </div>
                            </div>

                            <div class="film-card two-col__wrapper" id="card-3">
                                <div class="wrapper">
                                    <h2>Iron man</h2>
                                </div>
                                <div class="wrapper align-center">
                                    <button class="outlined-button rounded-button button">Delete</button>
                                    <button class="light-filled-button rounded-button button">Edit</button>
                                </div>
                            </div>

                            <div class="film-card two-col__wrapper" id="card-4">
                                <div class="wrapper">
                                    <h2>Iron man</h2>
                                </div>
                                <div class="wrapper align-center">
                                    <button class="outlined-button rounded-button button">Delete</button>
                                    <button class="light-filled-button rounded-button button">Edit</button>
                                </div>
                            </div>

                            <div class="film-card two-col__wrapper" id="card-5">
                                <div class="wrapper">
                                    <h2>Iron man</h2>
                                </div>
                                <div class="wrapper align-center">
                                    <button class="outlined-button rounded-button button">Delete</button>
                                    <button class="light-filled-button rounded-button button">Edit</button>
                                </div>
                            </div>

                            <div class="film-card two-col__wrapper" id="card-6">
                                <div class="wrapper">
                                    <h2>Iron man</h2>
                                </div>
                                <div class="wrapper align-center">
                                    <button class="outlined-button rounded-button button">Delete</button>
                                    <button class="light-filled-button rounded-button button">Edit</button>
                                </div>
                            </div>

                            <div class="film-card two-col__wrapper" id="card-7">
                                <div class="wrapper">
                                    <h2>Iron man</h2>
                                </div>
                                <div class="wrapper align-center">
                                    <button class="outlined-button rounded-button button">Delete</button>
                                    <button class="light-filled-button rounded-button button">Edit</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="centered-content" id="film-add right">
                        <form method="post">
                            <label for="film-id">Film ID</label>
                            <input type="text" placeholder="Film ID | Text" name="film-id" id="film-id" required/>
                            <label for="film-name">Film Name</label>
                            <input type="text" placeholder="Film Name | Text" name="film-name" id="film-name" required/>
                            <label for="film-price">Ticket Price</label>
                            <input type="number" placeholder="Film Price | Number" name="film-price" id="film-price"
                                   required/>
                            <label for="film-genre">Genre</label>
                            <input type="text" placeholder="Film Genre | Text" name="film-genre" id="film-genre"
                                   required/>
                            <input type="submit" class="primary-filled-button button" value="Add Film">
                        </form>
                    </div>

                </div>
            </div>

            <div id="admin-registration-content" class="tab-content container">
                <h2>Registrations Management</h2>
            </div>
        </div>
    </div>
</section>
