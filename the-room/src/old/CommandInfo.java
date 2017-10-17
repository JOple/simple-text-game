package old;

public class CommandInfo {

	public String action;
	public String argument;
	public String command;
	public String entireInput;

	public CommandInfo() { }
	public CommandInfo(String action) {
		this();
		this.action = action;
	}
	public CommandInfo(String action, String argument) {
		this(action);
		this.argument = argument;
	}
	public CommandInfo(String action, String argument, String command) {
		this(action, argument);
		this.command = command;
	}
	public CommandInfo(String action, String argument, String command, String entireInput) {
		this(action, argument, command);
		this.entireInput = entireInput;
	}

	public String getAction() {
		return action;
	}
	public String getArgument() {
		return argument;
	}
	public String getCommand() {
		return command;
	}
	public String getEntireInput() {
		return entireInput;
	}

	@Override
	public String toString() {
		return "CommandInfo [action=" + action + ", argument=" + argument + ", command=" + command
				+ ", entireInput=" + entireInput + "]";
	}
}
