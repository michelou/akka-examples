package com.alexandreesl.json
 
import com.alexandreesl.model.Account
import spray.json.DefaultJsonProtocol
 
object JsonParsing extends DefaultJsonProtocol {
 
  implicit val accountFormat = jsonFormat13(Account)
 
}
