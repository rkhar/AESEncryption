package project.encryptrion.univeristy

import java.security.{KeyPairGenerator, SecureRandom}
import java.util.concurrent.TimeUnit

import javax.crypto.{Cipher, KeyGenerator}
import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import com.sun.crypto.provider.AESKeyGenerator
import project.encryptrion.univeristy.model.{Decrypt, Encrypt}
import project.encryptrion.univeristy.service.AESFunctionality

import scala.concurrent.ExecutionContext

object Boot extends App {

  implicit val timeout = Timeout(10, TimeUnit.SECONDS) // 10 seconds time out if the future didn't get anything yet.
  implicit val ec: ExecutionContext = ExecutionContext.global

  val system = ActorSystem("cipher")
  val aesEncrypter = system.actorOf(Props[AESFunctionality])

  println("Starting AES key generation.")
  var aesInstance = KeyGenerator.getInstance("AES")
  aesInstance.init(128) // Can't go lower due to limitations of Oracle Java. Download JCA to have 256 bit length.
  var aesKey = aesInstance.generateKey()
  println("Finished AES key generation.")

  var sample = "RASSUL KHAR"
  for(i <- 0 to 3000) {
    sample += "a"
    //println(i)
  }
  sample += "..."

  // Encrypting and decrypting the sample variable.
  println(s"Starting encrypting and decrypting: ${sample}")
  (aesEncrypter ? Encrypt(sample.getBytes, aesKey)).onSuccess({
    case a: Array[Byte] =>
      println(s"Decrypting... (${new String(a)})")
      (aesEncrypter ? Decrypt(a, aesKey)).onSuccess({
        case b: Array[Byte] =>
          println(s"DECRYPTED (aes): ${new String(b)}")
      })
  })

  // Encrypting and decrypting an inline message.
  (aesEncrypter ? Encrypt("This is really awesome.".getBytes, aesKey)).onSuccess({
    case a: Array[Byte] =>
      println(s"Decrypting... (${new String(a)})")
      (aesEncrypter ? Decrypt(a, aesKey)).onSuccess({
        case b: Array[Byte] =>
          println(s"DECRYPTED (aes): ${new String(b)}")
      })
  })

  // Encrypting and decrypting a string about Kylo Ren.
  (aesEncrypter ? Encrypt("Knights of Ren, Kylo Ren is a savage.".getBytes, aesKey)).onSuccess({
    case a: Array[Byte] =>
      println(s"Decrypting... (${new String(a)})")
      (aesEncrypter ? Decrypt(a, aesKey)).onSuccess({
        case b: Array[Byte] =>
          println(s"DECRYPTED (aes): ${new String(b)}")
      })
  })
}
