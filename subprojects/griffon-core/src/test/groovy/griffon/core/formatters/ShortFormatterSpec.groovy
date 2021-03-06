/*
 * Copyright 2008-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package griffon.core.formatters

import spock.lang.Unroll

import static griffon.core.formatters.ShortFormatter.PATTERN_CURRENCY
import static griffon.core.formatters.ShortFormatter.PATTERN_PERCENT

@Unroll
class ShortFormatterSpec extends FormatterSpecSupport {
    void "Short '#value' produces literal '#literal'"() {
        given:
        ShortFormatter formatter = new ShortFormatter()

        when:
        String str = formatter.format(value)
        Short val = formatter.parse(str)

        then:
        str == literal
        val == value

        where:
        value        | literal
        100 as short | '100'
    }

    void "Short '#value' with pattern '#pattern' produces literal '#literal'"() {
        given:
        ShortFormatter formatter = new ShortFormatter(pattern)

        when:
        String str = formatter.format(value)
        Short val = formatter.parse(str)

        then:
        str == literal
        val == value

        where:
        pattern          | value        | literal
        PATTERN_CURRENCY | null         | null
        PATTERN_PERCENT  | null         | null
        PATTERN_CURRENCY | 100 as short | '$100.00'
        PATTERN_PERCENT  | 1 as short   | '100%'
        null             | 100 as short | '100'
        ''               | 100 as short | '100'
        '##.0'           | 20 as short  | '20.0'
    }

    void "Parse error for pattern '#pattern' with literal '#literal'"() {
        given:
        ShortFormatter formatter = new ShortFormatter(pattern)

        when:
        formatter.parse(literal)

        then:
        thrown(ParseException)

        where:
        pattern          | literal
        PATTERN_CURRENCY | 'abc'
        PATTERN_PERCENT  | 'abc'
    }

    void "Illegal pattern '#pattern'"() {
        when:
        new ShortFormatter(pattern)

        then:
        thrown(IllegalArgumentException)

        where:
        pattern << [';garbage*@%&']
    }
}
