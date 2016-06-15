import org.apache.shiro.authc.credential.HashedCredentialsMatcher
import org.apache.shiro.crypto.hash.Sha512Hash;

beans = {
    credentialMatcher(HashedCredentialsMatcher) {
        storedCredentialsHexEncoded = true
        hashAlgorithmName = Sha512Hash.ALGORITHM_NAME
    }
}