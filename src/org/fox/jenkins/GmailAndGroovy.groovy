package org.fox.jenkins

import jakarta.mail.*
import jakarta.mail.search.AndTerm;
import jakarta.mail.search.FlagTerm
import jakarta.mail.search.ReceivedDateTerm
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
        def username = "bpradeep272@gmail.com"
        def password = "zvha hzai lvtn lbrd"

        Properties props = new Properties()
        props.setProperty("mail.store.protocol", "imaps")
        props.setProperty("mail.imap.host", host)
        props.setProperty("mail.imap.port", port)
        props.setProperty("mail.imap.ssl.enable", "true");
        try {
        def session = Session.getDefaultInstance(props);
        def store = session.getStore("imaps")

        store.connect(host, username, password)
        def folder = store.getFolder("INBOX")

        folder.open(Folder.READ_ONLY)

        Flags seen = new Flags(Flags.Flag.SEEN);
//        FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
            SearchTerm searchTermTime = new ReceivedDateTerm(DateTerm.EQ, oneHourBack);
            SearchTerm searchTermFlag = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
            SearchTerm searchTermSubject = new SubjectTerm("Jenkins");
            SearchTerm[] arr = new SearchTerm[]{searchTermTime, searchTermFlag, searchTermSubject};
//searchTerm.match(searchTermFt);
            SearchTerm searchTerm = new AndTerm(arr);

        Message[] msgs = folder.search(searchTerm);
//
        FetchProfile fetchProfile = new FetchProfile()
        fetchProfile.add(FetchProfile.Item.ENVELOPE)
        folder.fetch(msgs,fetchProfile)
//
        def mapValue = new HashMap();
        for ( test in msgs ) {
//            mapValue.put("receivedDate", ${msgs[i].receivedDate})
//            mapValue.put("from", ${msgs[i].from})
//            mapValue.put("subject", ${msgs[i].subject})
//            println"${msgs[i].receivedDate}"
////            println "${msgs[i].sender}"
//            println "${msgs[i].from}"
//            println "${msgs[i].subject}"
//            msgs[i].writeTo(System.out)
            mapValue.put("message",test.getSubject())
        }
            return mapValue
        } catch (e) {
            println(e)
            return e
        }
    }
}
