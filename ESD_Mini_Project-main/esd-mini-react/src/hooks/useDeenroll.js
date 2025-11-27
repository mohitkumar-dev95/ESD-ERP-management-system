import { useState } from "react";
import axios from "axios";
import config from "../config";

const useDeenroll = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  const deenroll = async (token, studentId, courseToRemove, courseToAdd, refetchCallback) => {
    setIsLoading(true);
    setError(null);

    try {
      const response = await axios.post(
        `${config}/api/v1/student/deenroll`,
        {
          studentId,
          courseToRemove,
          courseToAdd,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        }
      );

      // Optional callback for side effects (e.g., re-fetching courses)
      if (refetchCallback) {
        await refetchCallback();
      }

      setIsLoading(false);
      return response.data; // Return the API response
    } catch (err) {
      setIsLoading(false);
      const errorMessage = err.response?.data?.message || "Something went wrong";
      setError(errorMessage);
      throw new Error(errorMessage); // Rethrow for caller handling
    }
  };

  const resetError = () => {
    setError(null);
  };

  return { deenroll, isLoading, error, resetError };
};

export default useDeenroll;
