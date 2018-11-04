package com.beelego.sbadmin.local.notifier;

import de.codecentric.boot.admin.event.ClientApplicationEvent;
import de.codecentric.boot.admin.notify.AbstractStatusChangeNotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author : hama
 * @since : created in  2018/6/19
 */
@Component
public class MailNotifier extends AbstractStatusChangeNotifier {

    @Autowired
    private MailSender sender;
    private final SpelExpressionParser parser = new SpelExpressionParser();
    private String[] to = new String[]{"renns@newhope.cn"};
    private String[] cc;
    private String from = "xwjrejb@163.com";
    private Expression text = this.parser.parseExpression("#{application.name} (#{application.id})\nstatus changed from #{from.status} to #{to.status}\n\n#{application.healthUrl}", ParserContext.TEMPLATE_EXPRESSION);;
    private Expression subject = this.parser.parseExpression("#{application.name} (#{application.id}) is #{to.status}", ParserContext.TEMPLATE_EXPRESSION);;



    protected void doNotify(ClientApplicationEvent event) {
        EvaluationContext context = new StandardEvaluationContext(event);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(this.to);
        message.setFrom(this.from);
        message.setSubject((String) this.subject.getValue(context, String.class));
        message.setText((String) this.text.getValue(context, String.class));
        message.setCc(this.cc);
        this.sender.send(message);
    }

}
