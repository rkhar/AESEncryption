package project.encryptrion.univeristy.service

import java.security.Key

import javax.crypto.Cipher
import akka.actor.Actor
import akka.actor.Actor.Receive
import project.encryptrion.univeristy.model.{Decrypt, Encrypt}

class AESFunctionality extends Actor {
  override def receive: Receive = {
    case e: Encrypt =>
      val cipher: Cipher = Cipher.getInstance("AES")
      cipher.init(Cipher.ENCRYPT_MODE, e.key)
      val encryptedBytes = cipher.doFinal(e.bytes)
      sender ! encryptedBytes

    case d: Decrypt =>
      val cipher: Cipher = Cipher.getInstance("AES")
      cipher.init(Cipher.DECRYPT_MODE, d.key)
      val decryptedBytes = cipher.doFinal(d.bytes)
      sender ! decryptedBytes
  }

}
