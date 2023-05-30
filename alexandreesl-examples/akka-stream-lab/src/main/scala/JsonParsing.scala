package com.alexandreesl.json
 
import com.alexandreesl.model.Account
import spray.json.DefaultJsonProtocol
import spray.json.RootJsonFormat
 
object JsonParsing extends DefaultJsonProtocol {
 
  implicit val accountFormat: RootJsonFormat[Account] = jsonFormat13(Account)
 
}
