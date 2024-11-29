package org.fox.jenkins

import jakarta.mail.*
import jakarta.mail.search.AndTerm;
import jakarta.mail.search.FlagTerm
import jakarta.mail.search.SearchTerm
import jakarta.mail.search.SubjectTerm;

class GmailAndGroovy {
    def jenkins

    GmailAndGroovy(jenkins) {
        this.jenkins = jenkins
    }

    def readEmail() {
        def host = "imap.gmail.com"
        def port = "993"
        def username = "vijay.ibox@gmail.com"
        def password = "xktj umqz wpxl qjio"

        Properties props = new Properties()
        props.setProperty("mail.store.protocol", "imaps")
        props.setProperty("mail.imap.host", host)
        props.setProperty("mail.imap.port", port)
        props.setProperty("mail.imap.ssl.enable", "true");
        def listValue = new ArrayList<>();
        try {
        def session = Session.getDefaultInstance(props);
        def store = session.getStore("imaps")

        store.connect(host, username, password)
        def folder = store.getFolder("INBOX")

        folder.open(Folder.READ_WRITE)

        Flags seen = new Flags(Flags.Flag.SEEN);
        FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
            SearchTerm searchTermFlag = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
            SearchTerm searchTermSubject = new SubjectTerm("Your access to Connected Services has expired");
//            SearchTerm[] arr = new SearchTerm[]{searchTermFlag, searchTermSubject};
            SearchTerm[] arr = [searchTermFlag, searchTermSubject]
////searchTerm.match(searchTermFt);
            SearchTerm searchTerm = new AndTerm(arr);


        Message[] msgValue = folder.search(searchTerm);
        FetchProfile fetchProfile = new FetchProfile()
        fetchProfile.add(FetchProfile.Item.ENVELOPE)
        folder.fetch(msgValue,fetchProfile)
        for ( msgVal in msgValue ) {
            listValue.add("receivedDate : " + msgVal.getReceivedDate())
            listValue.add("from : " + msgVal.getFrom()[0])
            listValue.add("subject : " + msgVal.getSubject())
            msgVal.setFlag(Flags.Flag.SEEN, true)
        }
            return "Success output" + listValue.toString()
        } catch (e) {
            println(e.message)
            listValue.add(e.message)
            return "Failed output" + listValue.toString()
        }
    }
}
