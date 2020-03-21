package InternetRadio;

public enum RadioStation {
    ANTENNEBAYERN("http://stream.antenne.de/antenne"),
    BAYERN1("http://br-br1-schwaben.cast.addradio.de/br/br1/schwaben/mp3/128/stream.mp3"),
    BAYERN2("http://br-br2-sued.cast.addradio.de/br/br2/sued/mp3/128/stream.mp3"),
    BAYERN3("http://br-edge-2066.fra-lg.cdn.addradio.net/br/br3/live/mp3/128/stream.mp3"),
    BRKLASSIK("http://br-brklassik-live.cast.addradio.de/br/brklassik/live/mp3/128/stream.mp3"),
    BRHEIMAT("http://br-brheimat-live.cast.addradio.de/br/brheimat/live/mp3/128/stream.mp3"),
    DASDING("https://swr-edge-1032-dus-dtag-cdn.cast.addradio.de/swr/dasding/live/mp3/128/stream.mp3"),
    SWR3("https://swr-edge-2035-fra-lg-cdn.cast.addradio.de/swr/swr3/live/mp3/128/stream.mp3");

    private final String mp3url;

    private RadioStation(String mp3url){
        this.mp3url = mp3url;
    }

    public String getMp3url(){
        return mp3url;
    }
}
