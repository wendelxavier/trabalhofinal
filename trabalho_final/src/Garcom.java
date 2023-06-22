public class Garcom {
    private String nomeGarcom, dataNasc, email, sexo, cpf, telefone;
    private float salarioFixo;

    public Garcom () {
    }

    public Garcom(String nomeGarcom, String cpf, String dataNasc, String email, String telefone, String sexo, float salarioFixo) {
        this.nomeGarcom = nomeGarcom;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.email = email;
        this.telefone = telefone;
        this.sexo = sexo;
        this.salarioFixo = salarioFixo;
    }


    public String getNomeGarcom() {

        return nomeGarcom;
    }

    public void setNomeGarcom(String nomeGarcom) {

        this.nomeGarcom = nomeGarcom;
    }

    public String getCpf() {

        return cpf;
    }

    public void setCpf(String cpf) {

        this.cpf = cpf;
    }

    public String getDataNasc() {

        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {

        this.dataNasc = dataNasc;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getTelefone() {

        return telefone;
    }

    public void setTelefone(String telefone) {

        this.telefone = telefone;
    }

    public String getSexo() {

        return sexo;
    }

    public void setSexo(String sexo) {

        this.sexo = sexo;
    }

    public float getSalarioFixo() {

        return salarioFixo;
    }

    public void setSalarioFixo(float salarioFixo) {

        this.salarioFixo = salarioFixo;
    }
}
