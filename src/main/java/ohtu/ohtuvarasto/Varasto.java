package ohtu.ohtuvarasto;

public class Varasto {

    // --- piilotettu tietorakenteen toteutus: ---
    private double tilavuus; // paljonko varastoon mahtuu, > 0
    private double saldo; // paljonko varastossa on nyt, >= 0

    // --- konstruktorit: ---
    public Varasto(double tilavuus) { // tilavuus on annettava
        this.setTilavuus(tilavuus);
    }

    public Varasto(double tilavuus, double alkuSaldo) { // kuormitetaan
        this.setTilavuus(tilavuus);
        this.setSaldo(alkuSaldo);
    }

    // --- ottavat aksessorit eli getterit: ---
    public double getSaldo() {
        return saldo;
    }

    public void setTilavuus(double tilavuus) {
        if (tilavuus > 0) {
            this.tilavuus = tilavuus;
        } else {
            this.tilavuus = 0;
        }
    }

    public void setSaldo(double alkuSaldo) {
        this.saldo = 0;
        if (alkuSaldo < 0) {
            return;
        }
        if (alkuSaldo < this.tilavuus) {
            this.saldo = alkuSaldo;
        } else {
            this.saldo = this.tilavuus;
        }
    }

    public double getTilavuus() {
        return tilavuus;
    }

    public double paljonkoMahtuu() { // huom: ominaisuus voidaan myös laskea
        return tilavuus - saldo; // ei tarvita erillistä kenttää vielaTilaa tms.
    }

    // --- asettavat aksessorit eli setterit: ---
    public void lisaaVarastoon(double maara) {
        if (maara < 0) {
            return; // tällainen pikapoistuminenkin!
        }
        if (maara <= paljonkoMahtuu()) {
            saldo = saldo + maara; // ihan suoraan sellaisinaan
        } else {
            saldo = tilavuus; // täyteen ja ylimäärä hukkaan!
        }
    }

    public double otaVarastosta(double maara) {
        if (maara < 0) {
            return 0.0; // tällainen pikapoistuminenkin!
        }
        if (maara > saldo) { // annetaan mitä voidaan
            double kaikkiMitaVoidaan = saldo;
            saldo = 0.0; // ja tyhjäksi menee
            return kaikkiMitaVoidaan; // poistutaan saman tien
        }
        // jos tänne päästään, kaikki pyydetty voidaan antaa
        saldo = saldo - maara; // vähennetään annettava saldosta
        return maara;
    }

    // --- Merkkijonoesitys Varasto-oliolle: ----
    public String toString() {
        return ("saldo = " + saldo + ", vielä tilaa " + paljonkoMahtuu());
    }
}