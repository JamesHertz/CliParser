class Plans{
	// I used this think to organize my mind :)
	// How I intend to develop this project

	enum DataType {
		DECIMAL, STRING, INTERGER;
	}

	interface Argument{
		String toString();
		int toInteger();
		double toDecimal();
	}

	interface Format{
		int numOfArgs();
		DataType getType(int idx) throws OutOfBoundException;
		Iterator<DataType> getFormat();
	}

	interface Comamd<T>{
		String comandName();
		Format argsFormat();
		void runCommand(CommandContext<T> context);
	}

	interface CommandContext<T>{
		Iterator<Argument> arguments();
		T context();
	}

	interface CmdProgram<T>{
		void addCommand(Command<T> cmd);
		void run();
	}
	// POSSIBLE USER INTERFACE:
	class Main{
		/*
			My ideas are possible but I we have to take in accout the '-parameters' in
			the compilation process.

			classes I will have to work with:
				-> Class
				-> Method
				-> Parameters

			Is it worthed having description in args?
		 */

		/*
			A set of answer that I need to have first
		 */
		@Command(name="student", desc="simple stuffs")
		public static void handeStudent(CmdCtx<GradesManager> ctx, @Arg("student number") int number ){

		}

		@Command(name="top", desc="list all students by avg grade")
		public static void handleTop(CmdCtx<GradesManager> ctx, int age){

		}
	}


}






