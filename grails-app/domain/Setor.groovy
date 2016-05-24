/**
 * Created by Reuel on 23/05/2016.
 */
class Setor {
    static belongsTo = [estacionamento: Estacionamento]
    static hasMany = [vagas: Vaga]
    String nome


    def getNomeEstacionamento(){
        return estacionamento.getNome()
    }

}
