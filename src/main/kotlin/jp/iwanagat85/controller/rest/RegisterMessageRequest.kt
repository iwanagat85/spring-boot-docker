package jp.iwanagat85.controller.rest

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class RegisterMessageRequest @JsonCreator constructor(@JsonProperty("message") val message: String)
