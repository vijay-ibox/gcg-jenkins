package org.fox.jenkins

import jakarta.mail.*
import jakarta.mail.search.AndTerm;
import jakarta.mail.search.FlagTerm
import jakarta.mail.search.OrTerm
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
        def username = "gcgap.info@gmail.com"
        def password = "wihj djyl uvyj gtxt"

        Properties props = new Properties()
        props.setProperty("mail.store.protocol", "imaps")
        props.setProperty("mail.imap.host", host)
        props.setProperty("mail.imap.port", port)
        props.setProperty("mail.imap.ssl.enable", "true");
        def responseValue = new ArrayList<>()
        try {
            def session = Session.getDefaultInstance(props);
            def store = session.getStore("imaps")

            store.connect(host, username, password)
            def folder = store.getFolder("INBOX")

            folder.open(Folder.READ_WRITE)

            Flags seen = new Flags(Flags.Flag.SEEN);
            FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
            SearchTerm searchTermFlag = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
            SearchTerm searchTermFlag1 = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
            SearchTerm searchTermSubject = new SubjectTerm("Your access to Connected Services has expired");
            SearchTerm searchTermSubject1 = new SubjectTerm("Verify your account");
            SearchTerm searchTerm = new AndTerm(searchTermFlag,searchTermSubject);
            SearchTerm searchTerm1 = new AndTerm(searchTermFlag1,searchTermSubject1);
            SearchTerm[] searchArray = [searchTerm, searchTerm1]
            SearchTerm finalSearchTerm = new OrTerm(searchArray);

            Message[] msgValue = folder.search(finalSearchTerm);
            FetchProfile fetchProfile = new FetchProfile()
            fetchProfile.add(FetchProfile.Item.ENVELOPE)
            folder.fetch(msgValue,fetchProfile)

            for ( msgVal in msgValue ) {
                responseValue.add("receivedDate : " + msgVal.getReceivedDate().toString())
                responseValue.add("from : " + msgVal.getFrom()[0].toString())
                responseValue.add("subject : " + msgVal.getSubject().toString())
                msgVal.setFlag(Flags.Flag.SEEN, true)
            }
            return responseValue.toString()
        } catch (exception) {
            jenkins.println(exception.message)
            return exception
        }
    }
}
