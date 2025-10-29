package com.anrisys.teamflow.auth.app.service;

public record RegisterCommand(String email, String password, String confirmPassword) {

}
