package com.example.cadastro.domain

data class Formulario(
    val nome: String,
    val telefone: String,
    val email: String,
    val receberEmails: Boolean,
    val sexo: String,
    val cidade: String,
    val estado: String
)