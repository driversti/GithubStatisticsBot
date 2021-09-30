package dev.seniorjava.githubstatisticsbot.factories

import com.pengrad.telegrambot.model.CallbackQuery
import com.pengrad.telegrambot.model.Update
import spock.lang.Specification
import spock.lang.Subject

class CommitChosenEventFactoryUnitTest extends Specification {

    @Subject
    private factory = new CommitChosenEventFactory()

    def "should check whether it is possible to handle the given Update"() {
        expect:
        factory.canCreateEvent(givenUpdate) == expected

        where:
        givenUpdate                                                    || expected
        null                                                           || false
        new Update()                                                   || false
        new Update(callback_query: new CallbackQuery())                || false
        new Update(callback_query: new CallbackQuery(data: "/commit")) || true
    }
}
