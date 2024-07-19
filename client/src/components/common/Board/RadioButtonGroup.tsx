interface RadioButtonGroupProps {
	selectedStatus: string;
	onStatusSelect: (status: string) => void;
}

function RadioButtonGroup({
	selectedStatus,
	onStatusSelect,
}: RadioButtonGroupProps) {
	const getStatusLabel = (status: string) => {
		if (status === "live") {
			return "실시간";
		}
		if (status === "scheduled") {
			return "예정된";
		}
		return "종료된";
	};

	return (
		<div className="radio-buttons-container flex items-center space-x-6">
			{["live", "scheduled", "ended"].map((status) => (
				<div
					key={status}
					className="radio-button relative inline-block cursor-pointer"
				>
					<label
						htmlFor={status}
						className="radio-button__label inline-block pl-8 mb-2 relative font-size-16 cursor-pointer transition-all"
					>
						<input
							id={status}
							type="radio"
							name="status"
							value={status}
							className="radio-button__input absolute opacity-0 w-0 h-0 peer"
							checked={selectedStatus === status}
							onChange={() => onStatusSelect(status)}
						/>
						<span className="radio-button__custom absolute top-1/2 left-0 transform -translate-y-1/2 w-5 h-5 border-2 border-black rounded-full transition-all duration-300 peer-checked:w-3 peer-checked:h-3 peer-checked:bg-red-500" />
						{getStatusLabel(status)}
					</label>
				</div>
			))}
		</div>
	);
}

export default RadioButtonGroup;
